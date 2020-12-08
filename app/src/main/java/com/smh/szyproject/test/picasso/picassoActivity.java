package com.smh.szyproject.test.picasso;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.squareup.picasso.Picasso;

/**
 * author : smh
 * date   : 2020/11/30 14:25
 * desc   : 图片加载框架 类似Glide
 */
public class picassoActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        Picasso.with(this)
//                .load("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
//                .placeholder(R.drawable.picture_audio_placeholder)    //图片没加载出来之前，显示的
//                .centerCrop()//
//        .into(imageView);
    }
}
