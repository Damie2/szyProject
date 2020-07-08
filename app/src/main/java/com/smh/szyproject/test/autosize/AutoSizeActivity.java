package com.smh.szyproject.test.autosize;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/7/6 18:05
 * desc   :
 */
public class AutoSizeActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    int i = 0;

    @Override
    public void init(Bundle savedInstanceState) {
       new Thread(()->{  }).start();
    }
}
