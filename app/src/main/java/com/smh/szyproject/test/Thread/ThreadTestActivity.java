package com.smh.szyproject.test.Thread;

import android.os.Bundle;
import android.os.Looper;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/7/23 14:50
 * desc   :
 */
public class ThreadTestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        L.e("init");
        getContentView().postDelayed(() -> {
            L.e("线程开始");
            /**
             * 当你的线程想拥有自己的MessageQueue的时候要先
             * Looper.prepare()
             * 然后Looper.loop()
             *
             * 主线程有自己的消息队列
             * 一般线程创建的时候没有自己的消息队列
             * 所以处理时就要在主线程中完成
             *
             *
             *MessageQueue是啥，那玩意是个消息队列
             *
             */
            Looper.prepare();

            Looper.loop();
        }, 2000);
    }
}
