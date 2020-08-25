package com.smh.szyproject.mvp.presenter;

import android.content.Context;


import com.smh.szyproject.other.Rx.ExceptionHandle;
import com.smh.szyproject.common.base.BaseObserver;
import com.smh.szyproject.mvp.bean.testStatus;
import com.smh.szyproject.mvp.module.TestContract;
import com.smh.szyproject.net.RetrofitUtil;
import com.smh.szyproject.other.utils.L;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestPresenter implements TestContract.presenter {
    private Context context;
    private TestContract.View view;

    public TestPresenter(Context context, TestContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getValue() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1021615516688609282");
        RetrofitUtil.getInstance().initRetrofit().test(null)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void next(JSONObject data) {

                        //c


//                        Refund r = new Refund();
//                        String jsonStr = JSONObject.toJSONString(r);
//
//
//                        String jsonStr = "{\"msg\":\"ZhangSan\"}";
//                        Refund r = JSONObject.toJavaObject(jsonStr,Refund.class);
//
//                        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
//                        Refund r = JSONObject.parseObject(jsonStr,Refund.class)
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });

    }

    @Override
    public void sendStatusResult(testStatus testStatus) {

        RetrofitUtil.getInstance().initRetrofit().testStatus(testStatus)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
                .subscribe(new BaseObserver<JSONObject>() {
                    @Override
                    public void next(JSONObject data) {
                        L.e("这里:" +data.toString());
//                        view.sendRequest(data);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });

    }
}
