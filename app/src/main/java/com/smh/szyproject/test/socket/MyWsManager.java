package com.smh.szyproject.test.socket;

import android.content.Context;

import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;
import com.smh.szyproject.other.utils.L;
import com.umeng.message.proguard.l;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/**
 * author : smh
 * date   : 2020/6/28 16:00
 * desc   :
 */
class MyWsManager {

    private static final String DEF_RELEASE_URL = "ws://192.168.1.65:10001/";     //连接地址
    private static MyWsManager wsManager;
    private static WsManager myWsManager;
    private Context context;

    //单例
    public static MyWsManager getInstance() {
        if (wsManager == null) {
            synchronized (MyWsManager.class) {
                if (wsManager == null) {
                    wsManager = new MyWsManager();
                }
            }
        }
        return wsManager;
    }

    public void init(Context context) {
        try {

            myWsManager = new WsManager.Builder(context)
                    .client(
                            new OkHttpClient().newBuilder()
                                    .pingInterval(5, TimeUnit.SECONDS)
                                    .retryOnConnectionFailure(true)
                                    .build())
                    .needReconnect(true)
                    .wsUrl(DEF_RELEASE_URL)
                    .build();
            //.needReconnect(true)                  //是否需要重连
            //.setHeaders(null)                     //设置请求头
            //.setReconnnectInterval(10*1000)       //设置重连步长(ms)
            //.setReconnnectIMaxTime(30*1000)       //设置重连最大时长
            myWsManager.setWsStatusListener(wsStatusListener);
            myWsManager.startConnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WsStatusListener wsStatusListener = new WsStatusListener() {
        @Override
        public void onOpen(Response response) {
            L.e("服务器连接成功");
        }

        @Override
        public void onMessage(String text) {
            //在这里接收和处理收到的ws数据吧
            L.e("onMessage"+text + "\n\n");
        }

        @Override
        public void onMessage(ByteString bytes) {
            L.e("onMessage"+bytes);
        }

        @Override
        public void onReconnect() {
            L.e("onClosing 服务器重连接中...");
        }

        @Override
        public void onClosing(int code, String reason) {
            L.e("onClosing 服务器连接关闭中");

            //这一步我个人认为是比较骚的操作,上面提及了设备会出现断开后无法连接的情况,那这种无法连接的情
            //况我发现有可能会卡在这个关闭过程中,因为如果是确实断开后会确实的启动重连机制,至于还有别的坑
            //我后面会补充;这里主要的目的就死让他跳出这个关闭中的状态,确实的关闭了ws先
            if (myWsManager != null) {
                myWsManager.stopConnect();
                myWsManager.startConnect();
            }

        }

        @Override
        public void onClosed(int code, String reason) {

            L.e("服务器连接已关闭...");
        }

        @Override
        public void onFailure(Throwable t, Response response) {

            L.e("服务器连接失败");
        }
    };

    //发送ws数据
    public void sendDataD(String content) {

        if (myWsManager != null && myWsManager.isWsConnected()) {
            boolean isSend = myWsManager.sendMessage(content);
            if (isSend) {
                L.e("发送成功");
            } else {
                L.e("发送失败");
            }
        } else {
            //Toast.makeText(mContext, "请先连接服务器", Toast.LENGTH_SHORT).show();
            L.e("请先连接服务器");
        }

    }

    //断开ws
    public void disconnect() {
        if (myWsManager != null)
            myWsManager.stopConnect();
    }

}
