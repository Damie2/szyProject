package com.smh.szyproject.mvp.presenter;

import android.content.Context;

import org.json.JSONObject;

import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.mvp.bean.getName;
import com.smh.szyproject.mvp.bean.sendId;
import com.smh.szyproject.other.Rx.ExceptionHandle;
import com.smh.szyproject.common.base.BaseObserver;
import com.smh.szyproject.mvp.bean.testStatus;
import com.smh.szyproject.mvp.module.TestContract;
import com.smh.szyproject.net.RetrofitUtil;
import com.smh.szyproject.other.utils.GsonUtils;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.netTest.Bean2;
import com.smh.szyproject.test.netTest.TestBean;
import com.umeng.commonsdk.debug.D;


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
                        L.e("这里:" + data.toString());
//                        view.sendRequest(data);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });

    }

    @Override
    public void getResult(Test test) {
        RetrofitUtil.getInstance().initRetrofit().testStatus(test)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
//                .flatMap(new HandleFuc())
                .subscribe(new BaseObserver<Bean2>() {
                    @Override
                    public void next(Bean2 data) {
//                        L.e("鹅："+da.toString());
//                         Bean2 data = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(da ),Bean2.class);
                        L.e("？？："+GsonUtils.getGson().toJson(data));

                        if(data==null){
                            L.e("空");
                        }else{
                            L.e("不是空"+ data.getCommentNum());
                            L.e("不是空"+ data.getZanNum());
                            L.e(" "+GsonUtils.getGson().toJson(data));
                        }

//                        L.e("鹅："+data.getCommentNum());


//                        TestBean bean = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(data),TestBean.class);
//                        if(bean!=null){
//                            L.e("msg:"+bean.getMsg());
//                        }
//                        testStatus status = GsonUtils.getGson().fromJson(GsonUtils.getGson().toJson(data),testStatus.class);
//                        if(status!=null){
//                            L.e("status:"+status.getStatus());
//                        }

                        //                        view.sendRequest(data);

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code:" + e.code);
                    }
                });
    }



    @Override
    public void getName(sendId testStatus) {
        RetrofitUtil.getInstance().initRetrofit().sendID(testStatus)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
                .subscribe(new BaseObserver<getName>() {
                    @Override
                    public void next(getName data) {
                        L.e("这里:" + data.getName());
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });

    }

}
