package com.smh.szyproject.test;

import android.os.Bundle;
import android.widget.ImageView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.mvp.bean.getStatus;
import com.smh.szyproject.mvp.bean.sendId;
import com.smh.szyproject.mvp.bean.testStatus;
import com.smh.szyproject.mvp.module.TestContract;
import com.smh.szyproject.mvp.presenter.TestPresenter;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/3/9 14:01
 * desc   :
 */
public class TestActivity extends BaseActivity implements TestContract.View {
    ImageView iv;
    TestPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvvm;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        presenter = new TestPresenter(this, this);
        testStatus status = new testStatus();
        status.setStatus(0);
        presenter.sendStatusResult(status);
    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public void sendRequest(getStatus o) {
        L.e(""+o.getMessage());
    }

    @Override
    public void sendId(sendId testStatus) {

    }
}
