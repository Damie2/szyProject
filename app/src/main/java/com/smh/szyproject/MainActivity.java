package com.smh.szyproject;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.helper.DoubleClickHelper;
import com.smh.szyproject.helper.PopupWindowHelper;
import com.smh.szyproject.other.HintLayout;
import com.smh.szyproject.test.DesignPattern.Factory.FactoryTonA;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.L;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.layout)
    HintLayout layout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

}
