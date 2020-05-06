package com.smh.szyproject.test.interfaceTest;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.action.StatusAction;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.other.HintLayout;
import com.smh.szyproject.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   :
 */
public class interfaceTestA extends BaseActivity implements StatusAction {

    @BindView(R.id.layout)
    HintLayout layout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        InterfaceA inter = new AA();
        inter.test();
        getStatusBarConfig().statusBarDarkFont(true).init();

    }

    @Override
    public HintLayout getHintLayout() {
        return layout;
    }

}
