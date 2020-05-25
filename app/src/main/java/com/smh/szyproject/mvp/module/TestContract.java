package com.smh.szyproject.mvp.module;


import com.smh.szyproject.base.BasePresenter;
import com.smh.szyproject.base.BaseView;
import com.smh.szyproject.bean.getStatus;
import com.smh.szyproject.bean.getStatusResult;
import com.smh.szyproject.bean.testStatus;

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
