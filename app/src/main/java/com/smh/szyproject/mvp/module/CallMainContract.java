package com.smh.szyproject.mvp.module;

import com.smh.szyproject.common.base.BasePresenter;
import com.smh.szyproject.common.base.BaseView;
import com.smh.szyproject.mvp.bean.CallBean;
import com.smh.szyproject.mvp.bean.CallResult;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.bean.User;

import java.util.Map;

import cn.jmessage.support.okhttp3.MultipartBody;
import cn.jmessage.support.okhttp3.RequestBody;

/**
 * author : smh
 * date   : 2020/9/22 14:42
 * desc   :
 */
public interface CallMainContract {
    interface View extends BaseView<CallMainContract.presenter> {

        void getNumber(CallBean number);

    }

    interface presenter extends BasePresenter {

        void sendUser(ID user);  //设置内容


    }
}
