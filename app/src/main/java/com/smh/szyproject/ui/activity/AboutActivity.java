package com.smh.szyproject.ui.activity;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/4/2 14:58
 * desc   :
 */
public class AboutActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //字体颜色设置
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
