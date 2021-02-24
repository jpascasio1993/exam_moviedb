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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.exam.moviedb.R
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.ui.movie.MovieItemDetailActivity
import com.exam.moviedb.ui.movie.MovieItemDetailFragment
import com.exam.moviedb.utils.dateFormat
import java.util.Objects.equals

class MovieListAdapter(private val twoPane: Boolean, private val parentActivity: AppCompatActivity) :
    ListAdapter<Movie, RecyclerView.ViewHolder>(MovieListAdapterDiff) {
    private val FOOTER = 1;
    private val onClickListener: View.OnClickListener

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
        val titleView: AppCompatTextView = itemView.findViewById(R.id.id_text)
        val rating: AppCompatTextView = itemView.findViewById(R.id.id_rating)
        val poster: AppCompatImageView = itemView.findViewById(R.id.id_poster)
        val date: AppCompatTextView = itemView.findViewById(R.id.id_release_date)
    }

    inner class FooterListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == FOOTER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
            return FooterListViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return MovieListViewHolder(view)

    }

    override fun getItemViewType(position: Int): Int {
        if(position == super.getItemCount().minus(1))
            return FOOTER

        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MovieListViewHolder) {
            val movie = getItem(position);

            with(holder.itemView) {
                tag = movie.copy()
                setOnClickListener(onClickListener)
            }.apply {
                holder.titleView.text = movie.title.trim().replace(System.getProperty("line.separator")?:"", "")
                holder.rating.text = movie.voteAverage.toString()
                holder.date.text = movie.releaseDate.dateFormat()
                val glideRequestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .override(320)
                    .error(R.drawable.ic_baseline_error_24)
                    .placeholder(CircularProgressDrawable(parentActivity).apply {
                        strokeWidth = 5f
                        centerRadius = 30f
                        start()
                    })
                Glide.with(parentActivity)
                    .load(parentActivity.getString(R.string.tmdb_pic_base_url, "${movie?.posterPath}"))
                    .error(R.drawable.ic_baseline_error_24)
                    .apply(glideRequestOptions)
                    .into(holder.poster)
            }
        }
    }

}

object MovieListAdapterDiff : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return equals(oldItem, newItem)
    }

}