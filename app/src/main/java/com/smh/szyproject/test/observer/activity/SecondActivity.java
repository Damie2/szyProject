package com.smh.szyproject.test.observer.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.test.observer.listener.ObserverListener;
import com.smh.szyproject.test.observer.listener.ObserverManager;
import com.smh.szyproject.other.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/10 18:04
 * desc   :
 */
public class SecondActivity extends BaseActivity implements ObserverListener {
    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //注册当前的Observer
        ObserverManager.getInstance().add(this);
        textView.setOnClickListener(v -> {startActivity(ThirdActivity.class); });
    }

    @Override
    public void observerUpDate(String content) {
        L.e(" Second的内容:" + content);
    }
}
