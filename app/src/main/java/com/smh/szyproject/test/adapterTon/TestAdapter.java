package com.smh.szyproject.test.adapterTon;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

public class TestAdapter extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //搞个接口，new出来它的实现类
        Ps2 ps2 = new UsbAdapter();
        ps2.isPs2();

        Ps2 ps21 = new UserAdapter1();
        ps21.isPs2();
    }
}
