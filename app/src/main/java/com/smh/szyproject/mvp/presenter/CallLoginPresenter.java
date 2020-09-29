package com.smh.szyproject.mvp.presenter;

import android.content.Context;

import com.smh.szyproject.common.base.BaseObserver;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.bean.User;
import com.smh.szyproject.mvp.module.CallLoginContract;
import com.smh.szyproject.net.RetrofitUtil;
import com.smh.szyproject.other.Rx.ExceptionHandle;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SPUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : smh
 * date   : 2020/9/22 14:43
 * desc   :
 */
public class CallLoginPresenter implements CallLoginContract.presenter {
    private Context context;
    private CallLoginContract.View view;

    public CallLoginPresenter(Context context, CallLoginContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void sendUser(User user) {
        RetrofitUtil.getInstance().initRetrofit().callLogin(user)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
                .subscribe(new BaseObserver<ID>() {
                    @Override
                    public void next(ID data) {
                        SPUtil.putInt("id",data.getId(),context);
                        view.close();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });
    }
}
