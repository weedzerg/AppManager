package com.manager.managerapp.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceRecyclerViewVertical(
        private var top: Int,
        private var edge: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = top
        outRect.left = edge
        outRect.right = edge
    }
}