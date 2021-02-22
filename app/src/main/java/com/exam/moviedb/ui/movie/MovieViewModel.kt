package com.exam.moviedb.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.Result
import com.exam.moviedb.data.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    private val movies = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = movies

    fun getMovies(page: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.getMovieList(page).also {
                GlobalScope.launch(Dispatchers.Main) {
                    if (it is Result.Success) {
                        val list = (movies.value ?: emptyList())
                        movies.value = list + it.data
                    }
                }
            }
        }
    }
}