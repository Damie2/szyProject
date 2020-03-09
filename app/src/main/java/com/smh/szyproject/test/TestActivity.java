package com.smh.szyproject.test;

import android.graphics.Color;
import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/3/9 14:01
 * desc   :
 */
public class TestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_mvvm;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        StatusBarUtil.setTransparent(this);
//        StatusBarUtil.setColor(this, Color.WHITE);
    }
}
