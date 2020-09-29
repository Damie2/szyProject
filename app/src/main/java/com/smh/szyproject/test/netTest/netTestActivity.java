package com.smh.szyproject.test.netTest;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.mvp.bean.getStatus;
import com.smh.szyproject.mvp.bean.sendId;
import com.smh.szyproject.mvp.module.TestContract;
import com.smh.szyproject.mvp.presenter.TestPresenter;
import com.smh.szyproject.other.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/8/25 16:39
 * desc   :
 */
public class netTestActivity extends BaseActivity implements View.OnClickListener , TestContract.View{


    TestPresenter presenter;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        presenter = new TestPresenter(this, this);
    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_next){
            request1();
        }
    }

    private void request1() {
        sendId test= new sendId() ;
        test.setId(1);
        presenter.getName(test);
    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public void sendRequest(getStatus data) {

    }

    @Override
    public void sendId(sendId testStatus) {

    }
}
