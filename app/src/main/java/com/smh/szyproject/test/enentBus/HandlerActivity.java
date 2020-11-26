package com.smh.szyproject.test.enentBus;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/11/24 14:32
 * desc   :  用handler往线程里发送数据
 */
public class HandlerActivity extends BaseActivity {

    Handler handler;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
            findViewById(R.id.tv_next).setOnClickListener((View v)->{

                byHandler();
            });
    }

    //使用handler来往线程里发送消息
    private void byHandler() {
        Message message = handler.obtainMessage();//获取消息
        message.arg1=1;//第一个参数是1
        message.obj="我是消息";
        handler.sendMessage(message);
    }


    class MyThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();//必须先prepare，然后再loop才能在线程里new一个handler
            handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    L.e(msg.obj+"");
                    super.handleMessage(msg);
                }
            };

            Looper.loop();
            super.run();
        }
    }


}
