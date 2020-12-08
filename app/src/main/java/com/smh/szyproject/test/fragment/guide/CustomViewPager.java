package com.smh.szyproject.test.fragment.guide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.smh.szyproject.other.utils.L;


/**
 * author : smh
 * date   : 2020/12/4 13:31
 * desc   :
 */
public class CustomViewPager extends ViewPager {

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        L.e("ViewGroup onTouchEvent");
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.e("ViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        L.e("ViewGroup onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

//    Main dispatchTouchEvent
//    ViewGroup dispatchTouchEvent
//    ViewGroup onInterceptTouchEvent
//    ViewGroup onTouchEvent
//    Main dispatchTouchEvent
//    ViewGroup dispatchTouchEvent
//    ViewGroup onTouchEvent

    /**
     * 往下触发的时候，最开始都是
     *      dispatchTouchEvent
     * 接着 onInterceptTouchEvent
     * 然后 onTouchEvent
     */




}
