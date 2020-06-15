package com.smh.szyproject.test.observer.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.test.observer.listener.ObserverListener;
import com.smh.szyproject.test.observer.listener.ObserverManager;
import com.smh.szyproject.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/10 18:04
 * desc   :
 * <p>
 * <p>
 * 观察者模式总结
 * <p>
 * 首先所有类都实现一个接口
 * 其次要有个管理这个接口的类来把这些接口汇总、更改等
 * 接着由ObserverManager 拿到回调的接口信息
 * 更改的时候循环遍历所有接口，给接口塞值
 * 就完成了Observer观察者模式的设计
 * <p>
 * <p>
 * 观察者模式就是，当一个对象的状态发生改变时，所有依赖于它的对象都能得到通知并被自动更新
 */
public class FirstActivity extends BaseActivity implements ObserverListener {
    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //注册当前的Observer
        ObserverManager.getInstance().add(this);
        textView.setOnClickListener(v -> {
            startActivity(SecondActivity.class);
        });
        int a = 10/0;
    }



    @Override
    public void observerUpDate(String content) {
        L.e("First的内容:" + content);
    }
}
