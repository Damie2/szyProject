package com.smh.szyproject.net.interceptor;


import com.smh.szyproject.MyApplication;
import com.smh.szyproject.other.utils.SPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieReadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", SPUtil.getString("cookiess", "", MyApplication.getContext()));
        return chain.proceed(builder.build());
    }
}
