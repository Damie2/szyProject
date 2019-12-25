package com.smh.szyproject.net;


import com.google.gson.JsonObject;
import com.smh.szyproject.bean.Test;
import com.smh.szyproject.bean.TestBean;

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
    Observable<JsonObject> test(@Body Test parameter);


}
