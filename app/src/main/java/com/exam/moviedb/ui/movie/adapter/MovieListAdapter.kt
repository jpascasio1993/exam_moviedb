package com.exam.moviedb.ui.movie.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.exam.moviedb.R
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.ui.movie.MovieItemDetailActivity
import com.exam.moviedb.ui.movie.MovieItemDetailFragment
import com.google.android.material.chip.Chip
import java.util.Objects.equals

class MovieListAdapter(val twoPane: Boolean, val parentActivity: AppCompatActivity) :
    ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(MyListAdapterDiff) {

    private val onClickListener: View.OnClickListener
    private val glideRequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .fitCenter()
        .override(320)

    init {
        onClickListener = View.OnClickListener { view ->
            val item = view.tag as Movie
            if (twoPane) {
                val fragment = MovieItemDetailFragment.newInstance().apply {
                    arguments = Bundle().apply {
                        putParcelable(MovieItemDetailFragment.ARG_ID, item)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
                return@OnClickListener
            }

            val intent = Intent(view.context, MovieItemDetailActivity::class.java).apply {
                putExtra(MovieItemDetailFragment.ARG_ID, item)
            }
            view.context.startActivity(intent)
        }
    }

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.id_text)
        val rating: AppCompatTextView = itemView.findViewById(R.id.id_rating)
        val poster: AppCompatImageView = itemView.findViewById(R.id.id_poster)
        val date: AppCompatTextView = itemView.findViewById(R.id.id_release_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = getItem(position);

        with(holder.itemView) {
            tag = movie.copy()
            setOnClickListener(onClickListener)
        }.let {
            holder.titleView.text = movie.title
            holder.rating.text = movie.voteAverage.toString()
            holder.date.text = movie.releaseDate
            Glide.with(parentActivity).load("https://image.tmdb.org/t/p/w500${movie?.posterPath}")

                .apply(glideRequestOptions)
                .into(holder.poster)
        }

    }
}

object MyListAdapterDiff : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return equals(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return equals(oldItem, newItem)
    }

}