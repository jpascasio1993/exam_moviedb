package com.exam.moviedb.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.Result
import com.exam.moviedb.data.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movies

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    init {
        _loading.value = false
    }

    fun getMovies(page: Int) {
        if(_loading.value == true) return
        _loading.value = true
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.getMovieList(page).also {
                GlobalScope.launch(Dispatchers.Main) {
                    if (it is Result.Success) {
                        val list = (_movies.value ?: emptyList())
                        _movies.value = (list + it.data)
                    }
                    _loading.value = false
                }
            }
        }
    }

    fun loadMore() {
        _movies.value?.last()?.page?.plus(1)?.let {
            getMovies(it)
        }
    }

    fun refresh(page: Int) {
        if(_isRefreshing.value == true) return

        _isRefreshing.value = true
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.getMovieList(page).also {
                GlobalScope.launch(Dispatchers.Main) {
                    if (it is Result.Success) {
                        _movies.value = it.data
                    }
                    _isRefreshing.value = false
                }
            }
        }
    }
}