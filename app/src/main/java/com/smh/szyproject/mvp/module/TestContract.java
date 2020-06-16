package com.smh.szyproject.mvp.module;


import com.smh.szyproject.common.base.BasePresenter;
import com.smh.szyproject.common.base.BaseView;
import com.smh.szyproject.mvp.bean.getStatus;
import com.smh.szyproject.mvp.bean.testStatus;

public interface TestContract {
    interface View extends BaseView<presenter> {

        void setContent(String content);  //设置内容

        void sendRequest(getStatus data);  //设置内容
    }

    interface presenter extends BasePresenter {

        void getValue(); //获取内容


        void sendStatusResult(testStatus testStatus);

    }
}
