package com.smh.szyproject.test.zkr.dispatchTouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 不能滑动的ViewPager
 * 
 * @author Kevin
 * @date 2015-8-11
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	
	//决定事件是否中断
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;//不拦截事件, 让嵌套的子viewpager有机会响应触摸事件
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 重写ViewPager滑动事件, 改为什么都不做
		return true;
	}

}
