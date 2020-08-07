package com.smh.szyproject.ui.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Create by smh on 2019/2/19.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        //不是第一个的格子都设一个左边和底部的间距
//        outRect.left = space;
//        outRect.bottom = space;
//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//        if (parent.getChildLayoutPosition(view) % 3 == 0) {
//            outRect.left = 0;
//        }


        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildLayoutPosition(view) == 0) {
//            outRect.top = space;
//        } else {
//            outRect.top = 0;
//
//        }
        }

}
