package com.smh.szyproject.test.interfaceTest3;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   : 第三步 类实现接口，并且new的时候关联接口
 */
public class interfaceTestD extends BaseActivity implements InterfaceD {

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

//        DD c1 = new DD(this, code -> {
//                L.e("先是这里啊");
//        });

        new EE(this);//看构造方法

        new EE(e->{

        });
    }


    @Override
    public void OnListener(String Code) {
        L.e("再是这里啊" + Code);
    }
}
