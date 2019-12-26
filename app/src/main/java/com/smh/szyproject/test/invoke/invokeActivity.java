package com.smh.szyproject.test.invoke;


import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.ToastUtils;

public class invokeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        ToastUtils.showToastForText(this,"123");
        return R.layout.activity_mvvm;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}
