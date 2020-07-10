package com.smh.szyproject.test.fragment.guide;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.SPUtil;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Create by smh on 2018/9/14.
 * 引导页面
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.vp_pager)
    public ViewPager mViewPager;
    @BindView(R.id.btn_start)
    public Button btnStart;

    private final int RC_LOCATION_CONTACTS_PERM = 100;

    private ArrayList<ImageView> mImageViewList;
    private int[] mImageIds = new int[]{R.drawable.guide_1,
            R.drawable.guide_2};

    @Override
    public void init(Bundle savedInstanceState) {
        btnStart.setOnClickListener(this);


        mImageViewList = new ArrayList<ImageView>();

        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);
        }

        mViewPager.setAdapter(new MyAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageIds.length - 1) {// 最后页面显示开始体验
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    btnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == mImageIds.length - 1) {// 最后页面显示开始体验
//                    btnStart.setVisibility(View.VISIBLE);
//                } else {
//                    btnStart.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset,
//                                       int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }



    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_guide;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            SPUtil.putBoolean("is_guide_show", true, this);
//            startActivity(new Intent(this, WebActivity.class));
            finish();
        }
    }
}
