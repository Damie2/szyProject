package com.smh.szyproject.test.jetpack.bilibiliJetPack.lifecycle6;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/28 15:25
 * desc   :
 */
public class LifeCycleActivity extends BaseActivity {
    @BindView(R.id.lifecycle_custom)
    public CustomChromoeter chromoeter;
    private long watchedTime;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_lifecycle;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //也就是说这玩意是关联activity的生命周期的
        getLifecycle().addObserver(chromoeter);
    }
}

