package com.smh.szyproject.mvp.presenter;

import android.content.Context;

import com.smh.szyproject.common.base.BaseObserver;
import com.smh.szyproject.mvp.bean.CallBean;
import com.smh.szyproject.mvp.bean.CallResult;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.bean.User;
import com.smh.szyproject.mvp.module.CallMainContract;
import com.smh.szyproject.net.RetrofitUtil;
import com.smh.szyproject.other.Rx.ExceptionHandle;
import com.smh.szyproject.other.utils.L;

import org.json.JSONObject;

import java.util.Map;

import cn.jmessage.support.okhttp3.MultipartBody;
import cn.jmessage.support.okhttp3.RequestBody;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : smh
 * date   : 2020/9/22 14:43
 * desc   :
 */
public class CallMainPresenter implements CallMainContract.presenter {
    private Context context;
    private CallMainContract.View view;

    public CallMainPresenter(Context context, CallMainContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void sendUser(ID user) {
        RetrofitUtil.getInstance().initRetrofit().callGetUser(user)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
                .subscribe(new BaseObserver<CallBean>() {
                    @Override
                    public void next(CallBean data) {
                        view.getNumber(data);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });
    }


}
