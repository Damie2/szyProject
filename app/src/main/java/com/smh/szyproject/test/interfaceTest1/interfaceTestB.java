package com.smh.szyproject.test.interfaceTest1;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   : 第三步 类实现接口，并且new的时候关联接口
 */
public class interfaceTestB extends BaseActivity implements InterfaceB{
    BB bb;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }


    @Override
    public void init(Bundle savedInstanceState) {
        bb = new BB(this,this);
    }

    @Override
    public void OnListener(String Code) {
        L.e("收到了"+Code);
    }
}
