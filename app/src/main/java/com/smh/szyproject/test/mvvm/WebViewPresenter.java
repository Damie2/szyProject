package com.smh.szyproject.test.mvvm;

import android.content.Context;


import com.smh.szyproject.Rx.ExceptionHandle;
import com.smh.szyproject.base.BaseObserver;
import com.smh.szyproject.net.RetrofitUtil;
import com.smh.szyproject.utils.L;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WebViewPresenter implements WebViewContract.presenter {
    private Context context;
    private WebViewContract.View view;

    public WebViewPresenter(Context context, WebViewContract.View view) {
        this.context = context;
        this.view = view;
    }



    @Override
    public void sendParameter(PhoneParameter parameter) {
        RetrofitUtil.getmInstance().initRetrofit().sendParameter(parameter)
                .subscribeOn(Schedulers.io())//指定线程中去调上游的subscribe方法
                .observeOn(AndroidSchedulers.mainThread())//收到数据后在指定的线程中调用下游的回调方法
                .subscribe(new BaseObserver<Result>() {
                    @Override
                    public void next(Result data) {
                        view.getResult(data);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });
    }



}
