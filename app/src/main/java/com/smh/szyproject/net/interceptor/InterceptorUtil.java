package com.smh.szyproject.net.interceptor;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 日志拦截器
 */
public class InterceptorUtil {
    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
//                Log.w(MainUtil.logger, "log: " + message);
//                L.d("日志是："+message);
            }
            //设置打印数据的级别
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}

