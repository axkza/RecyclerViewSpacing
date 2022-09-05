package com.example.library

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/***
 * RecyclerView通用间距
 *
 * 支持LinearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager
 *
 * Created by kai
 */
class RecyclerViewSpacing : ItemDecoration {

    // item的水平、垂直方向的间距
    private var verticalSpacing = 0
    private var horizontalSpacing = 0

    // recyclerview 左右分别额外的边距，可正可负，效果如|◼ ◼ ◼ ◼|、|  ◼ ◼ ◼ ◼  |
    private var extraSpacing = 0

    // item占满一行时，该item是否需要左右间距
    var needVerticalSpacingInSingleLine = true
    var needHorizontalSpacingInSingleLine = true

    constructor(spacing: Float) {
        initSpacing(spacing, spacing, 0f)
    }

    constructor(spacing: Float, extraSpacing: Float) {
        initSpacing(spacing, spacing, extraSpacing)
    }

    constructor(verticalSpacing: Float, horizontalSpacing: Float, extraSpacing: Float) {
        initSpacing(verticalSpacing, horizontalSpacing, extraSpacing)
    }

    private fun initSpacing(verticalSpacing: Float, horizontalSpacing: Float, extraSpacing: Float) {
        this.horizontalSpacing = horizontalSpacing.dp2px()
        this.verticalSpacing = verticalSpacing.dp2px()
        this.extraSpacing = extraSpacing.dp2px()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        var spanCount = 1
        var spanSize = 1
        var spanIndex = 0

        parent.layoutManager?.run {
            when (this) {
                is StaggeredGridLayoutManager -> {
                    spanCount = this.spanCount
                    (view.layoutParams as StaggeredGridLayoutManager.LayoutParams)?.run {
                        if (isFullSpan) spanSize = spanCount
                        spanIndex = this.spanIndex
                    }
                }
                is GridLayoutManager -> {
                    spanCount = this.spanCount
                    spanSize = this.spanSizeLookup.getSpanSize(position)
                    spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
                }
                is LinearLayoutManager -> {
                    outRect.left = verticalSpacing
                    outRect.right = verticalSpacing
                    outRect.bottom = horizontalSpacing
                    return
                }
            }
        }

        if (spanSize == spanCount) {
            outRect.left = if (needVerticalSpacingInSingleLine) verticalSpacing + extraSpacing else 0
            outRect.right = if (needVerticalSpacingInSingleLine) verticalSpacing + extraSpacing else 0
            outRect.bottom = if (needHorizontalSpacingInSingleLine) horizontalSpacing else 0
        } else {
            val itemAllSpacing = (verticalSpacing * (spanCount + 1) + extraSpacing * 2) / spanCount
            val left = verticalSpacing * (spanIndex + 1) - itemAllSpacing * spanIndex + extraSpacing
            val right = itemAllSpacing - left
            outRect.left = left
            outRect.right = right
            outRect.bottom = horizontalSpacing
        }
    }
}