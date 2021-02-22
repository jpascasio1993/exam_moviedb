package com.exam.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.moviedb.ui.movie.MovieItemsFragment

class MainActivity : AppCompatActivity() {
//    private val movieViewModel: MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieItemsFragment.newInstance())
                .commitNow()
        }
//        val button = findViewById<AppCompatButton>(R.id.button)
//
//        button.setOnClickListener {
//            movieViewModel.getMovies(10)
//
//            movieViewModel.movieList.observe(this@MainActivity, {
//                if(it.isEmpty())
//                    return@observe
//                Log.i("flux", it?.get(0).toString())
//            })
//            movieViewModel.getMovies(11)
//        }

    }
}