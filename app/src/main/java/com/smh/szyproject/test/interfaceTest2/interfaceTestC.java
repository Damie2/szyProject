package com.smh.szyproject.test.interfaceTest2;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.L;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   : 第三步 类实现接口，并且new的时候关联接口
 */
public class interfaceTestC extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        new CC(this, new InterfaceC() {
            @Override
            public void OnListener(String Code) {
                L.e("这里啊：" + Code);
            }
        });
    }
}