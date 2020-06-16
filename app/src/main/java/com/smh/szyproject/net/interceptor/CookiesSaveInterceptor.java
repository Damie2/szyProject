package com.smh.szyproject.net.interceptor;


import com.smh.szyproject.MyApplication;
import com.smh.szyproject.other.utils.SPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CookiesSaveInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String header =originalResponse.header("Set-Cookie");
            SPUtil.putString("cookiess",header, MyApplication.getContext());
        }
        return originalResponse;
    }
}
