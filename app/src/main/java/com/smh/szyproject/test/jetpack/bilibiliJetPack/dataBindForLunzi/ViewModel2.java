package com.smh.szyproject.test.jetpack.bilibiliJetPack.dataBindForLunzi;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hjq.http.EasyHttp;
import com.hjq.http.lifecycle.ActivityLifecycle;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;
import com.smh.szyproject.easyNet.bean.ID;
import com.smh.szyproject.easyNet.bean.User;
import com.smh.szyproject.easyNet.request.LoginApi;
import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.other.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * author : smh
 * date   : 2020/9/29 16:44
 * desc   :
 */
public class ViewModel2 extends ViewModel implements OnHttpListener {
    private MutableLiveData<List<Test>> users;
    private AppCompatActivity lifecycle;//必须用

    private OnHttpListener listener;

    public MutableLiveData<List<Test>> getUsers(AppCompatActivity lifecycle) {
        this.lifecycle = lifecycle;
        if (users == null) {
            users = new MutableLiveData<>();
        }
        return users;
    }

    public MutableLiveData<List<Test>> getUsers(AppCompatActivity lifecycle, OnHttpListener listener) {
        this.lifecycle = lifecycle;
        this.lifecycle = lifecycle;
        if (users == null) {
            users = new MutableLiveData<>();
        }
        return users;
    }


    public void get() {
        EasyHttp.post(lifecycle).api(new LoginApi().setAuthor(new User("admin"))).request(new HttpCallback<ID>(this) {
        });


        EasyHttp.post(lifecycle).api(new LoginApi().setAuthor(new User("admin"))).request(new HttpCallback<ID>(listener) {
        });
    }

    public void loadUsers() {
        List<Test> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Test test = new Test();
            test.setMsg(i);
            list.add(test);
        }
        users.setValue(list);
    }

    @Override
    public void onSucceed(Object result) {//成功，ojbk
        L.e("拿到结果了" + result.toString());
    }

    @Override
    public void onFail(Exception e) {

    }
}
