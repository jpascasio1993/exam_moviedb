package com.exam.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.exam.moviedb.R
import com.exam.moviedb.data.domain.Movie
import com.google.android.material.appbar.CollapsingToolbarLayout

class MovieItemDetailFragment: Fragment() {

    private var item: Movie? = null

    companion object {
        const val ARG_ID = "MovieItemDetailFragment"
        fun newInstance() = MovieItemDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            if(it.containsKey(ARG_ID)) {
                item = it.getParcelable(ARG_ID)
                view?.let { v ->
                    v.findViewById<TextView>(R.id.item_detail).text = item?.overview
                    v.findViewById<AppCompatTextView>(R.id.item_detail_title).text = item?.title
                    v.findViewById<AppCompatTextView>(R.id.item_detail_rating).text = item?.voteAverage.toString()
                    v.findViewById<AppCompatTextView>(R.id.item_detail_popularity).text = "Popularity"
                    v.findViewById<AppCompatTextView>(R.id.item_detail_revenue).text = "Revenue"
                    Glide.with(this).load("https://image.tmdb.org/t/p/w500${item?.posterPath}").into(v.findViewById<AppCompatImageView>(R.id.image_poster));
                }

                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = " "
            }
        }
    }
}