package com.smh.szyproject.test.dagger2.module;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.L;

import javax.inject.Inject;

/**
 * author : smh
 * date   : 2019/12/27 14:00
 * desc   : 第四步,调用
 */
public class UserDaggerTest extends BaseActivity {
    @Inject
    User user;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        DaggerUserComponent.builder().build().inject(this);//不带参数的构造

        L.e(""+user.getName());
    }
}
