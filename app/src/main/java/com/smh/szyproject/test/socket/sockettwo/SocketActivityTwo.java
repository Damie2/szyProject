package com.smh.szyproject.test.socket.sockettwo;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.OnClick;


/**
 * 参考
 * https://www.jianshu.com/p/9302f8552a7d
 */
public class SocketActivityTwo extends BaseActivity implements View.OnClickListener {

    JWebSocketClient client;

    private String json = "{\"action\":\"registry\",\"userId\":\"108554\",\"memberId\":\"1382\",\"name\":\"用户250\",\"ua\":\"PC\"}";
    @Override
    public int getLayoutId() {
        return R.layout.activity_socket_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        URI uri = URI.create("ws://192.168.1.65:10001");
        L.e("init");
         client = new JWebSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                //message就是接收到的消息
                L.e("收到的信息:"+message);
            }
        };
    }

    @OnClick({R.id.connect, R.id.disconnect, R.id.Receive, R.id.send})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connect:
                try {
                    client.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.disconnect:
                try {
                    if (null != client) {
                        client.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    client = null;
                }
                break;
            case R.id.send:
                if (client != null && client.isOpen()) {
                    client.send("ping");
                }
                break;
            default:
                break;
        }
    }

    public class JWebSocketClient extends WebSocketClient {
        public JWebSocketClient(URI serverUri) {
            super(serverUri, new Draft_6455());
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            L.e("onOpen");
            client.send(json);
        }

        @Override
        public void onMessage(String message) {
            L.e("onMessage:"+message);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            L.e("onClose:"+code+","+reason);
        }

        @Override
        public void onError(Exception ex) {
            L.e("onError");
            ex.printStackTrace();
        }
    }
}