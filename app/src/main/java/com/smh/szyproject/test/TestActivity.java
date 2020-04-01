package com.smh.szyproject.test;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.common.image.ImageLoader;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/9 14:01
 * desc   :
 */
public class TestActivity extends BaseActivity {
    ImageView iv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvvm;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImageLoader.with(this).load("http://img14.360buyimg.com/n1/jfs/t28306/211/882124028/609313/1947379a/5bffa0a4N1903d27d.png").circle().into(iv);
    }
}
