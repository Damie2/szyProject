package com.smh.szyproject.test.dagger;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;

public class Dagger2Test extends BaseActivity {
//    @Inject//dagger2会告诉你去找ApiService的实例，它会去comment里去找,这里是UserComponet
//    ApiService mApiService;
//    private UserManager mUserManager;

    UserManager mUserManager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }



}
