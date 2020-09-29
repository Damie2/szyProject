package com.smh.szyproject.mvp.module;

import com.smh.szyproject.common.base.BasePresenter;
import com.smh.szyproject.common.base.BaseView;
import com.smh.szyproject.mvp.bean.User;

/**
 * author : smh
 * date   : 2020/9/22 14:42
 * desc   :
 */
public interface CallLoginContract {
    interface View extends BaseView<CallLoginContract.presenter> {
        void close();
    }

    interface presenter extends BasePresenter {
        void sendUser(User user);  //设置内容
    }
}
