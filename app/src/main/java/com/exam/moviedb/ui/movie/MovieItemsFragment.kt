package com.exam.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.exam.moviedb.MainActivity
import com.exam.moviedb.R
import com.exam.moviedb.ui.movie.adapter.MovieListAdapter
import com.exam.moviedb.ui.movie.adapter.SpacingItemDecoration
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
            // large-screen layouts (res/values-w600dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        setupRecyclerView(view!!.findViewById(R.id.item_list), savedInstanceState)
        setupSwipeToRefresh()
    }

    private fun setupRecyclerView(recycler: RecyclerView, savedInstanceState: Bundle?) {

        if(twoPane) {
            val linearLayoutManager = LinearLayoutManager(context);
            val dividerItemDecoration = DividerItemDecoration(
                context,
                linearLayoutManager.orientation
            )

            recycler.apply {
                layoutManager = linearLayoutManager
                addItemDecoration(dividerItemDecoration)
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        // scroll down
                        if (dy > 0) {

                            val lastVisiblePosition: Int =
                                linearLayoutManager.findLastVisibleItemPosition()
                            if (lastVisiblePosition == recyclerView.childCount) {
                                movieViewModel.loadMore()
                            }
                        }
                    }
                })
                setHasFixedSize(true)
                adapter = MovieListAdapter(twoPane, activity as AppCompatActivity)
            }
        } else {
            val span = (resources.configuration.screenWidthDp / 320).plus(1)
            val gridLayoutManager = GridLayoutManager(context, span).apply {
                val spanListener = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val size = recycler.adapter?.itemCount?.minus(1)
                        if(position == size) {
                            return span
                        }
                        return 1
                    }

                }
                spanSizeLookup = spanListener
            }
            recycler.apply{
                layoutManager = gridLayoutManager
                adapter = MovieListAdapter(twoPane, activity as AppCompatActivity)
                addItemDecoration(SpacingItemDecoration(10))
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        // scroll down
                        if (dy > 0) {
                            val visibleItemCount = gridLayoutManager.childCount;
                            val totalItemCount = gridLayoutManager.itemCount;
                            val pastVisiblesItems =
                                gridLayoutManager.findFirstVisibleItemPosition();
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                movieViewModel.loadMore()
                            }
                        }
                    }
                })
                setHasFixedSize(true)
            }
        }

        if (savedInstanceState == null) movieViewModel.getMovies(1)

        movieViewModel.movieList.observe(this@MovieItemsFragment, { movies ->
            (recycler.adapter as MovieListAdapter).submitList(movies)
        })
    }

    private fun setupSwipeToRefresh() {
        val swiper = view!!.findViewById<SwipeRefreshLayout>(R.id.swiper)

        swiper.apply {
            setOnRefreshListener {
                movieViewModel.refresh(1)
            }
        }

        movieViewModel.isRefreshing.observe(this@MovieItemsFragment, {
            swiper.isRefreshing = it
        })

    }
}