package com.smh.szyproject.test.jetpack.bilibiliJetPack.dataBing3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.smh.szyproject.mvp.bean.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * author : smh
 * date   : 2020/9/29 16:44
 * desc   :
 */
public class ViewModel1 extends ViewModel {
    private MutableLiveData<List<Test>> users;

    public MutableLiveData<List<Test>> getUsers(){
    
        if(users==null){
            users = new MutableLiveData<>();
        }
        return users;
    }

    public void loadUsers() {
        List<Test> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Test test = new Test();
            test.setMsg(i);
            list.add(test);
        }
        users.setValue(list);
    }

}
