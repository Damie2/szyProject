package com.smh.szyproject.mvp.module;


import com.smh.szyproject.base.BasePresenter;
import com.smh.szyproject.base.BaseView;

public interface TestContract {
    interface View extends BaseView<presenter> {

        void setContent(String content);  //设置内容

    }

    interface presenter extends BasePresenter {

        void getValue(); //获取内容

    }
}
