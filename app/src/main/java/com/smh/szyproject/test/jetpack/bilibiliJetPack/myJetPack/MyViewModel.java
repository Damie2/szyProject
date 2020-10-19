package com.smh.szyproject.test.jetpack.bilibiliJetPack.myJetPack;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.smh.szyproject.common.base.BaseObserver;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.bean.User;
import com.smh.szyproject.mvp.bean.ZmContact;
import com.smh.szyproject.net.RetrofitUtil;
import com.smh.szyproject.other.Rx.ExceptionHandle;
import com.smh.szyproject.other.utils.L;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : smh
 * date   : 2020/9/30 10:26
 * desc   :
 */
public class MyViewModel extends ViewModel {
    private MutableLiveData<List<ZmContact>> list;

    private MutableLiveData<ID> status;

    Context context;

    public MutableLiveData<List<ZmContact>> getUsers(Context context) {
        this.context = context;
        if (list == null) {
            list = new MutableLiveData<>();
        }
        return list;
    }

    public MutableLiveData<ID> getStatus() {
        if (status == null) {
            status = new MutableLiveData<>();
        }
        return status;
    }

    public void loadStatus(User user) {
        RetrofitUtil.getInstance().initRetrofit().callLogin(user)
                .subscribeOn(Schedulers.io())//在线程中请求数据，这个就是相当于new一个线程了
                .observeOn(AndroidSchedulers.mainThread())//在主线程中处理结果
                .subscribe(new BaseObserver<ID>() {
                    @Override
                    public void next(ID data) {
                        status.setValue(data);
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        L.e(e.getMessage() + "code=" + e.code);
                    }
                });
    }

    public void loadList() {
        List<ZmContact> zm = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ZmContact test = new ZmContact();
            test.setPhone("phone:" + i);
            test.setName("name:" + i);
            test.setAddress("address:" + i);
            zm.add(test);
        }
        list.setValue(zm);

        User  user = new User();
        user.setPhone("admin");
        loadStatus(user);

    }
}
