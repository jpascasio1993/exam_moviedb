package com.exam.moviedb.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.moviedb.R
import com.exam.moviedb.data.domain.Movie

class MovieItemDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail2)
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        val item = intent.getParcelableExtra<Movie>(MovieItemDetailFragment.ARG_ID)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = MovieItemDetailFragment.newInstance().apply {
                arguments = Bundle().apply {
                    putParcelable(MovieItemDetailFragment.ARG_ID, item)
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }
}