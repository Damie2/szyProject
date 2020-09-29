package com.smh.szyproject.test.jetpack.bilibiliJetPack.navigation4;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/9/28 13:33
 * desc   :  它来帮助我们更好的切换fragment
 */

//主要关注三个点

/**
 * 1、NavGraph 用来显示fragment的视图
 * 2、NavHost  是fragment的一个容器
 * 3、NavController  对他们进行的一个管理
 *
 */

public class NavigationActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_navigation;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //我放弃了，看以前的吧
    }
}
