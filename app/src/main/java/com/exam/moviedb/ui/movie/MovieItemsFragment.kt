package com.exam.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.moviedb.MainActivity
import com.exam.moviedb.R
import com.exam.moviedb.ui.movie.adapter.MovieListAdapter
import org.koin.android.ext.android.inject


class MovieItemsFragment : Fragment() {
    private val movieViewModel: MovieViewModel by inject()
    private var twoPane: Boolean = false

    companion object {
        fun newInstance() = MovieItemsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_item_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val toolbar = view!!.findViewById<Toolbar>(R.id.toolbar)
        (activity as MainActivity).setSupportActionBar(toolbar)

        if (view!!.findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        setupRecyclerView(view!!.findViewById(R.id.item_list), savedInstanceState)
    }

    private fun setupRecyclerView(recycler: RecyclerView, savedInstanceState: Bundle?) {
//        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val lastVisiblePosition: Int = (recycler.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
//                if (lastVisiblePosition == recyclerView.childCount) {
//                    movieViewModel.loadMore()
//                }
//            }
//        })

        recycler.adapter =
            MovieListAdapter(twoPane, activity as AppCompatActivity)

        if (savedInstanceState == null) movieViewModel.getMovies(1)

        movieViewModel.movieList.observe(this@MovieItemsFragment, { movies ->
            (recycler.adapter as MovieListAdapter).submitList(movies)
        })
    }
}