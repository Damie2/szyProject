package com.smh.szyproject.test.lanmbda;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.base.BasePresenter;
import com.smh.szyproject.bean.testStatus;
import com.smh.szyproject.test.interfaceTest2.CC;
import com.smh.szyproject.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/10 14:29
 * desc   :
 */
public class LanmbdaActivity extends BaseActivity {
    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        textView.setOnClickListener(v -> {
        });

        //线程和runnable
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        //等效于
        new Thread(() -> {
            L.e("");
        }).start();

        //这个也是啊
        Runnable runnable = () -> L.e("");
        runnable.run();

//        Lanmbda1();
        Lanmbda2();
    }

    //有返回值的
    private void Lanmbda2() {
        //首先在()中定义此表达式里面需要接收变量s，后面的多行语句中就可以使用该变量了。注意：多行语句别少“；”号
        hi1((s) -> L.e("接收到的值是:" + s));

        hi1(new MyInterface1() {
            @Override
            public void lMethod1(String msg) {
                L.e("收到的值是1:"+msg);
            }
        });
    }

    private void hi1(MyInterface1 myInterface) {
        myInterface.lMethod1("???????");
    }


    interface MyInterface1 {
        void lMethod1(String msg);
    }

    //没返回值的
    private void Lanmbda1() {
        //方法
        hi(new MyInterface() {
            @Override
            public void lMethod() {
                L.e("??");
            }
        });
        //等效于
        hi(() -> L.e("???"));
    }

    private void hi(MyInterface myInterface) {
        myInterface.lMethod();
    }

    interface MyInterface {
        void lMethod();
    }
}
