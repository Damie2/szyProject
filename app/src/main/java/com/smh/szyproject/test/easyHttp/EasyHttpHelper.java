package com.smh.szyproject.test.easyHttp;

import androidx.lifecycle.LifecycleOwner;

import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;
import com.smh.szyproject.easyNet.bean.ID;
import com.smh.szyproject.easyNet.bean.User;
import com.smh.szyproject.easyNet.request.LoginApi;

/**
 * author : smh
 * date   : 2020/10/16 18:12
 * desc   :
 */
public class EasyHttpHelper {

    public static void test(LifecycleOwner lifecycleOwner, User user, OnHttpListener listener){
        EasyHttp.get(lifecycleOwner).api(new LoginApi().setAuthor(user)).request(new HttpCallback<ID>(listener) {});
    }
}
