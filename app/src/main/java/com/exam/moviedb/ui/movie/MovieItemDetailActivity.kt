package com.exam.moviedb.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exam.moviedb.R

class MovieItemDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = MovieItemDetailFragment.newInstance().apply {
                arguments = Bundle().apply {
                    putParcelable(MovieItemDetailFragment.ARG_ID, intent.getParcelableExtra(MovieItemDetailFragment.ARG_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }
}