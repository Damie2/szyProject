package com.smh.szyproject.test.mvvm;

import com.smh.szyproject.common.base.BasePresenter;
import com.smh.szyproject.common.base.BaseView;

/**
 * main下面的接口
 */
public interface WebViewContract {
    interface View extends BaseView<presenter> {

        void getResult(Result result);

    }

    interface presenter extends BasePresenter {


        void sendParameter(PhoneParameter parameter);//发送参数信息


    }

}
