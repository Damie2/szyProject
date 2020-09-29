package com.smh.szyproject.test.jetpack.bilibiliJetPack.dataBing3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * author : smh
 * date   : 2020/9/27 11:51
 * desc   :
 */
public class DataBindingViewModel extends ViewModel {
    private MutableLiveData<Integer> ck, jy;

    public MutableLiveData<Integer> getCk() {
        if (ck == null) {
            ck = new MutableLiveData<>();
            ck.setValue(0);
        }
        return ck;
    }

//    setValue用于当前线程
//    ck.postValue用于异步线程

    public MutableLiveData<Integer> getJy() {
        if (jy == null) {
            jy = new MutableLiveData<>();
            jy.setValue(0);
        }
        return jy;
    }

    public void addCk() {
        if(ck.getValue()!=null){
            ck.setValue(ck.getValue() + 1);
        }
    }

    public void addJay() {
        if(jy.getValue()!=null){
            jy.setValue(jy.getValue() + 1);
        }
    }
}
