package com.smh.szyproject.test.lanmbda;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

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
//        两个参数的
        LanmbdaTwo();
    }

    private void LanmbdaTwo() {
        testTwo testTwo = new testTwo() {
            @Override
            public void test(int x, int y) {
                L.e(x + y + "");
            }
        };
        testTwo.test(2, 3);
        testTwo testTwo1 = (int x, int y) -> {
            L.e(x + y + "");
        };
        testTwo1.test(1, 2);
        
        testTwo testTwo2 = (int x, int y) -> L.e(x + y + "");
        testTwo2.test(1, 2);


        Handler   mMainHandler=new Handler(msg -> {
            switch (msg.what) {
                case 0:

                    break;
            }
            return true;
        });
    }

    //有返回值的
    private void Lanmbda2() {
        //首先在()中定义此表达式里面需要接收变量s，后面的多行语句中就可以使用该变量了。注意：多行语句别少“；”号
        hi1((s) -> L.e("接收到的值是:" + s));

        hi1(new MyInterface1() {
            @Override
            public void lMethod1(String msg) {
                L.e("收到的值是1:" + msg);
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

    interface testTwo {
        void test(int x, int y);
    }
}
