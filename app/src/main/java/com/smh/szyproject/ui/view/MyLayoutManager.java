package com.smh.szyproject.ui.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;


public class MyLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    private int mDrift;//位移，用来判断移动方向
    private SnapHelper mSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;

    public MyLayoutManager(Context context) {
        super(context);
        mSnapHelper=new CustomSnapHelper();
    }

    public MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
            mSnapHelper = new LinearSnapHelper();
    }


    private RecyclerView view;
    @Override
    public void onAttachedToWindow(RecyclerView view) {
        this.view=view;
        view.addOnChildAttachStateChangeListener(this);
        mSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }
//当Item添加进来了  调用这个方法

    //
    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
//        播放视频操作 即将要播放的是上一个视频 还是下一个视频
        if (mDrift > 0) {
//            向上
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(view), true);
            }

        } else {
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(view), false);
            }
        }
    }

    public void setOnViewPagerListener(OnViewPagerListener mOnViewPagerListener) {
        this.mOnViewPagerListener = mOnViewPagerListener;
    }
    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View view = mSnapHelper.findSnapView(this);

                int position = getPosition(view);

                if (mOnViewPagerListener != null) {
                    mOnViewPagerListener.onPageSelected(position, position == getItemCount() - 1);

                }
//                postion  ---回调 ----》播放


                break;
        }
        super.onScrollStateChanged(state);
    }
    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        if (mDrift >= 0) {
            if (mOnViewPagerListener != null)
                mOnViewPagerListener.onPageRelease(true, getPosition(view));
        } else {
            if (mOnViewPagerListener != null)
                mOnViewPagerListener.onPageRelease(false, getPosition(view));
        }
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }
    public interface OnViewPagerListener {
        /*初始化完成*/
        void onInitComplete();

        /*释放的监听*/
        void onPageRelease(boolean isNext, int position);

        /*选中的监听以及判断是否滑动到底部*/
        void onPageSelected(int position, boolean isBottom);


    }
}