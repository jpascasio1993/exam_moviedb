package com.exam.moviedb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.exam.moviedb.R
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.utils.dateFormat

class MovieItemDetailFragment : Fragment() {

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
        val glideRequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .override(320)
            .error(R.drawable.ic_baseline_error_24)
            .placeholder(CircularProgressDrawable(context!!).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            })

        arguments?.let {
            if (it.containsKey(ARG_ID)) {
                item = it.getParcelable(ARG_ID)
                view?.let { v ->
                    v.findViewById<TextView>(R.id.item_detail).text = item?.overview
                    v.findViewById<AppCompatTextView>(R.id.item_detail_title).text = item?.title
                    v.findViewById<AppCompatTextView>(R.id.item_detail_rating).text =
                        item?.voteAverage.toString()
                    v.findViewById<AppCompatTextView>(R.id.item_detail_votes).text =
                        "${item?.voteCount.toString()} Votes"
                    v.findViewById<AppCompatTextView>(R.id.item_detail_date).text =
                        item?.releaseDate?.dateFormat()

                    Glide.with(this)
                        .load(
                        context?.getString(
                            R.string.tmdb_pic_base_url,
                            "${item?.backdropPath}"
                        )
                    )
                        .apply(glideRequestOptions)
                        .into(v.findViewById<AppCompatImageView>(R.id.item_detail_image_banner))

                    Glide.with(this)
                        .load(context?.getString(R.string.tmdb_pic_base_url, "${item?.posterPath}"))
                        .apply(glideRequestOptions)
                        .into(v.findViewById<AppCompatImageView>(R.id.item_detail_image_poster));
                }

                activity?.findViewById<Toolbar>(R.id.detail_toolbar)?.title = item?.title
            }
        }
    }
}