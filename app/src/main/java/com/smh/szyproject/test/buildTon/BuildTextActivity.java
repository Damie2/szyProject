package com.smh.szyproject.test.buildTon;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

public class BuildTextActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init
            (Bundle savedInstanceState) {
        Director director = new Director();
        Human human = director.createHumanByDirecotr(new SmartManBuilder());
        Human human1 = director.createHumanByDirecotr(new ActiveManBuilder());
    }
}
