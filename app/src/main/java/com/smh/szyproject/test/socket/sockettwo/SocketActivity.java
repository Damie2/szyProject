package com.smh.szyproject.test.socket.sockettwo;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.OnClick;


/**
 * 参考
 * https://www.jianshu.com/p/9302f8552a7d
 */
public class SocketActivity extends BaseActivity implements View.OnClickListener {
    private static final String HOST = "192.168.1.65";
    private static final int PORT = 10001;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;


    @Override
    public int getLayoutId() {
        return R.layout.test_activity_socket_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mExecutorService = Executors.newCachedThreadPool();
    }

    @OnClick({R.id.connect, R.id.disconnect, R.id.Receive, R.id.send})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connect:
                mExecutorService.execute(new connectService());  //在一个新的线程中请求 Socket 连接
                break;
            case R.id.disconnect:
                mExecutorService.execute(new sendService("0"));
                break;
            case R.id.send:
                mExecutorService.execute(new sendService("ping"));
                break;
            default:
                break;
        }
    }
    private class sendService implements Runnable {
        private String msg;

        sendService(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }



    private class connectService implements Runnable {
        @Override
        public void run() {//可以考虑在此处添加一个while循环，结合下面的catch语句，实现Socket对象获取失败后的超时重连，直到成功建立Socket连接
            try {

                Socket socket = new Socket(HOST, PORT);      //步骤一
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //步骤二
                        socket.getOutputStream(), "utf-8")), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                receiveMsg();
            } catch (Exception e) {
                L.e("出错了"+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void receiveMsg() {
        try {
            while (true) {                                      //步骤三
                if ((receiveMsg = in.readLine()) != null) {
                    L.e("receiveMsg:"+receiveMsg);
//                    runOnUiThread(()->{
//                        L.e("消息是:"+receiveMsg + "\n\n" );
//                    });
                }
            }
        } catch (IOException e) {
            L.e("receiveMsg: ");
            e.printStackTrace();
        }
    }


}