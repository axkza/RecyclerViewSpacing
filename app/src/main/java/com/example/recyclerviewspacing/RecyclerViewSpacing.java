package com.example.recyclerviewspacing;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/***
 * RecyclerView通用间距
 *
 * 支持LinearLayoutManager、GridLayoutManager、StaggeredGridLayoutManager
 *
 * Created by kai on 17/4/28.
 * ***/
public class RecyclerViewSpacing extends RecyclerView.ItemDecoration {
    private int verticalSpacing;
    private int horizontalSpacing;
    private int extraSpacing = 0;
    private boolean isLineWithVerticalSpacing = false;

    public RecyclerViewSpacing(int spacing) {
        this.initSpacing(spacing, spacing, 0);
    }

    public RecyclerViewSpacing(int spacing, int extraSpacing) {
        this.initSpacing(spacing, spacing, extraSpacing);
    }

    public RecyclerViewSpacing(int verticalSpacing, int horizontalSpacing, int extraSpacing) {
        this.initSpacing(verticalSpacing, horizontalSpacing, extraSpacing);
    }

    private void initSpacing(int verticalSpacing, int horizontalSpacing, int extraSpacing) {
        this.horizontalSpacing = dip2px((float) horizontalSpacing);
        this.verticalSpacing = dip2px((float) verticalSpacing);
        this.extraSpacing = dip2px((float) Math.abs(extraSpacing)) * (extraSpacing < 0 ? -1 : 1);
    }

    private int dip2px(float dpValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public void setLineWithVerticalSpacing() {
        this.isLineWithVerticalSpacing = true;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = 1;
        int spanSize = 1;
        int spanIndex = 0;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            if (((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).isFullSpan()) {
                spanSize = spanCount;
            }
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            spanSize = ((GridLayoutManager) layoutManager).getSpanSizeLookup().getSpanSize(position);
            spanIndex = ((androidx.recyclerview.widget.GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof LinearLayoutManager) {
            outRect.left = this.verticalSpacing;
            outRect.right = this.verticalSpacing;
            outRect.bottom = this.horizontalSpacing;
            return;
        }

        if (spanSize == spanCount) {
            outRect.left = this.isLineWithVerticalSpacing ? this.verticalSpacing + this.extraSpacing : 0;
            outRect.right = this.isLineWithVerticalSpacing ? this.verticalSpacing + this.extraSpacing : 0;
            outRect.bottom = 0;
        } else {
            int itemAllSpacing = (this.verticalSpacing * (spanCount + 1) + this.extraSpacing * 2) / spanCount;
            int left = this.verticalSpacing * (spanIndex + 1) - itemAllSpacing * spanIndex + this.extraSpacing;
            int right = itemAllSpacing - left;
            outRect.left = left;
            outRect.right = right;
            outRect.bottom = this.horizontalSpacing;
        }
    }
}
