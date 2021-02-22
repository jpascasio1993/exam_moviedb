package com.exam.moviedb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LiveData
import com.exam.moviedb.data.repository.IMovieRepository
import com.exam.moviedb.ui.movie.IMovieViewModel
import com.exam.moviedb.ui.movie.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<AppCompatButton>(R.id.button)

        button.setOnClickListener {
            movieViewModel.getMovies(10)

            movieViewModel.movieList.observe(this@MainActivity, {
                if(it.isEmpty())
                    return@observe
                Log.i("flux", it?.get(0).toString())
            })
            movieViewModel.getMovies(11)
        }

    }
}