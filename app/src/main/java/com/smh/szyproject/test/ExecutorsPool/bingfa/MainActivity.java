package com.smh.szyproject.test.ExecutorsPool.bingfa;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.smh.szyproject.R;

import java.util.concurrent.FutureTask;

/**
 * author : smh
 * date   : 2020/12/3 18:10
 * desc   :
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        for (int i=0;i<100;i++){//高并发
            final int finalI = i;
            Thread thread =new Thread(){
                @Override
                public void run() {
                    super.run();
//                    Log.e("jjjjjj", "runnable---->"+ finalI);
                }
            };
            ThreadPoolManager.getInstance().execute(new FutureTask<Object>(thread,null),null);
        }


        Thread thread =new Thread(){
            @Override
            public void run() {
                super.run();
                Log.e("jjjjjj", "runnable---->finalIrunnable");
            }
        };
        ThreadPoolManager.getInstance().execute(new FutureTask<Object>(thread,null),(long)10000);//延时执行


    }
}
