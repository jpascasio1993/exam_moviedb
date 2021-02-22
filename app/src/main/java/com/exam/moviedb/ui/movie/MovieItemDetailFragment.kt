package com.exam.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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
                }

                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = item?.title
            }
        }
    }
}