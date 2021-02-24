package com.exam.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.moviedb.ui.movie.MovieItemsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieItemsFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}