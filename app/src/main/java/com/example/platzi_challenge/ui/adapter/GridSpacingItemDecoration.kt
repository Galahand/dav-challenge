package com.example.platzi_challenge.ui.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val pixelSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager is GridLayoutManager) {
            val position = parent.getChildLayoutPosition(view)
            if (position == 0 || position == 1) {
                outRect.top = 0
            } else {
                outRect.top = pixelSize
            }

            if (position % 2 == 0) {
                outRect.left = 0
                outRect.right = pixelSize / 2
            } else {
                outRect.right = 0
                outRect.left = pixelSize / 2
            }
        } else {
            val position = parent.getChildLayoutPosition(view)
            if (position == 0) {
                outRect.top = 0
            } else {
                outRect.top = pixelSize
            }
        }
    }

}