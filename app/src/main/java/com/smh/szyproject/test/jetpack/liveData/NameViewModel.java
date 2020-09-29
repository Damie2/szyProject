package com.smh.szyproject.test.jetpack.liveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * author : smh
 * date   : 2020/9/21 17:58
 * desc   :
 */
public class NameViewModel extends ViewModel {
    //LiveData是个数据持有类
    private MutableLiveData<String> mCurrentName;

    private MutableLiveData<List<String>> mNameListData;

    public MutableLiveData<String> getmCurrentName() {
        if(mCurrentName==null){
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }
    public MutableLiveData<List<String>> getNameList(){
        if (mNameListData == null) {
            mNameListData = new MutableLiveData<>();
        }
        return mNameListData;
    }
}
