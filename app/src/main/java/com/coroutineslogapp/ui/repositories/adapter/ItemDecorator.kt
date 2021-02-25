package com.coroutineslogapp.ui.repositories.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.coroutineslogapp.R

class ItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            top = view.resources.getDimension(R.dimen.repository_item_margin_top_bottom).toInt()
            left = view.resources.getDimension(R.dimen.repository_item_margin_left_right).toInt()
            right = view.resources.getDimension(R.dimen.repository_item_margin_left_right).toInt()
            if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
                bottom =
                    view.resources.getDimension(R.dimen.repository_item_margin_top_bottom).toInt()
            }
        }
    }
}
