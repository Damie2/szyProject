package com.smh.szyproject.net;


import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.mvp.bean.TestBean;
import com.smh.szyproject.mvp.bean.testStatus;
import com.smh.szyproject.test.mvvm.PhoneParameter;
import com.smh.szyproject.test.mvvm.Result;

import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AllApi {

    @POST(ApiAddress.sendVersion)
    Observable<Object> test1(@Body Test parameter);

    @POST(ApiAddress.sendVersion)
    @FormUrlEncoded
    Observable<TestBean> test2(@FieldMap HashMap<String, Object> map);

    @POST(ApiAddress.sendVersion)
    Observable<JSONObject> test(@Body Test parameter);


    @POST(ApiAddress.getStatus)
    Observable<JSONObject> testStatus(@Body testStatus parameter);

    //1.安装APP后发送请求
    @POST(ApiAddress.sendParameter)
    Observable<Result> sendParameter(@Body PhoneParameter parameter);

}
