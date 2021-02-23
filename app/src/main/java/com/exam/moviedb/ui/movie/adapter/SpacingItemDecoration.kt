package com.exam.moviedb.ui.movie.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(private val space: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            left = space
            right = space
            bottom = space * 2
            top = 0
            if(parent.getChildLayoutPosition(view) == 0){
                top = space * 2
            }
        }
    }
}