package com.smh.szyproject.test.okHttpTest;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author : smh
 * date   : 2020/9/11 17:19
 * desc   :
 */
public class OkHttp3TestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        Request request = new Request.Builder()
                .url("www.baidu.com").build();
        //开启同步请求
        try {
            new OkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
