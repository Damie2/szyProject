package com.smh.szyproject.test.duotai;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/8/13 10:29
 * desc   :
 */
public class duotaiMainActivity extends BaseActivity {
    //程序员客栈  云沃客 解放号
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }
    //形参是父类的引用，实参是任何一个子类的引用
    //俩都继承Bird
    //都重写父类的moo方法
    //使用的时候像下面这样
    @Override
    public void init(Bundle savedInstanceState) {
        getContentView().postDelayed(()->{
            Parrot parrot  = new Parrot();
            Sparrow sparrow = new Sparrow();
            hit(parrot);
            hit(sparrow);

        },200);
    }

    private  static void hit(Parrot c){
        c.moo();
    }

    private  static void hit(Sparrow c){
        c.moo();
    }
}
