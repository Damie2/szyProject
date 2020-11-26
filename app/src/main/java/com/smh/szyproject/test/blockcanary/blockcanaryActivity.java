package com.smh.szyproject.test.blockcanary;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/11/26 14:02
 * desc   : UI卡顿检测
 */
public class blockcanaryActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        for(int i=0;i<100;i++){

        }
    }
}
