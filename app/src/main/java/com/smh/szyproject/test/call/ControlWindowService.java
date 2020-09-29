package com.smh.szyproject.test.call;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.arialyy.annotations.Upload;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.common.HttpOption;
import com.arialyy.aria.core.task.UploadTask;
import com.smh.szyproject.mvp.bean.CallBean;
import com.smh.szyproject.mvp.bean.CallResult;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.module.CallMainContract;
import com.smh.szyproject.mvp.presenter.CallMainPresenter;
import com.smh.szyproject.other.Rx.databus.RegisterRxBus;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.other.utils.FileUtil;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SPUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ControlWindowService extends Service implements CallMainContract.View {
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button button;
    private boolean type = true;
    Context context;
    contralListener contralListener;
    private TelephonyManager tm;
    private MyListener listener;
    private MediaRecorder mediaRecorder;
    CallMainPresenter presenter;
    CountDownTimer timer;
    long startTime;
    int duration;
    private String path;
    private String callId;
    private String phone;

    private volatile boolean isCalling = false;

    public void setCallback(contralListener listener, Context context) {//注意这里以单个回调为例  如果是向多个activity传送数据 可以定义一个回调集合 在此处进行集合的添加
        this.contralListener = listener;
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RxBus.getInstance().register(this);
        Aria.upload(this).register();
        initWindow();
        showFloatingWindow();
        startTimer();
        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        listener = new MyListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        presenter = new CallMainPresenter(this, this);
    }

    private void startTimer() {
        timer = new CountDownTimer(Long.MAX_VALUE, 15000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                向服务器请求数据，获得新的电话号码
                if (!isCalling) {
                    L.e("没在通话中，打电话");
                    ID id = new ID();
                    id.setId(SPUtil.getInt("id", 0, context));
                    presenter.sendUser(id);
                }else{
                    L.e("正在通话中，不打电话 ");
                }
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Override
    public void getNumber(CallBean bean) {
        callId = bean.getCall_id();
        phone = bean.getPhone();
//        bean.setPhone("10086");
        L.e("开始计时");
        contralListener.start(bean.getPhone());
    }

    private class MyListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            try {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE://空闲状态。
                        isCalling = false;
                        if (mediaRecorder != null) {
                            //8.停止捕获
                            mediaRecorder.stop();
                            //9.释放资源
                            mediaRecorder.release();
                            mediaRecorder = null;
                            //正常结束
                            sendStatusResult(2);
                        }
                        break;
                    case TelephonyManager.CALL_STATE_RINGING://零响状态。
//                        L.e("来电状态，电话铃声响起的那段时间或正在通话又来新电，新来电话不得不等待的那段时间");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK://通话状态
//                        L.e("通话状态");
//                        L.e("摘机状态，至少有个电话活动。该活动或是拨打（dialing）或是通话");
                        //开始录音
                        //1.实例化一个录音机
                        isCalling = true;
                        mediaRecorder = new MediaRecorder();
                        //2.指定录音机的声音源
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        //3.设置录制的文件输出的格式
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                        //4.指定录音文件的名称
                        path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".3gp";
                        File file = new File(path);
                        L.e("地址是:" + path);
                        mediaRecorder.setOutputFile(file.getAbsolutePath());
                        //5.设置音频的编码
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                        //6.准备开始录音
                        mediaRecorder.prepare();
                        //7.开始录音
                        mediaRecorder.start();
                        startTime = System.currentTimeMillis();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendStatusResult(int status) {
        HttpOption option = new HttpOption();
        Map<String, String> params = new HashMap<>();
        switch (status) {
            case 1://响铃30秒没打通
                params.put("status", "1");
                break;
            case 2://打通了
                duration = (int) ((System.currentTimeMillis() - startTime) / 1000);
                params.put("status", "2");
                params.put("callId", callId);
                params.put("phone", phone);
                params.put("duration", duration + "");
                break;
            default:
                break;
        }

        option.setParams(params);
        Aria.upload(this)
                .load(path) // 文件路径
                .setUploadUrl("http://dy.vk0.com/api/callResponse") // 设置文件保存路径
                .option(option)
                .create();
    }

    @Upload.onTaskComplete
    public void taskComplete(UploadTask task) {
        L.e("上传成功");
        if (task != null) {
            if (task.getEntity() != null) {
                L.e("上传成功返回数据（如果有的话）：" + task.getEntity().getResponseStr());
            }
        }
        FileUtil.deleteFile(path);
        path = "";
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mybinder;
    }

    private MyBinder mybinder = new MyBinder();


    public class MyBinder extends Binder {
        public ControlWindowService getService() {
            return ControlWindowService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            button = new Button(getApplicationContext());
            button.setText("已关闭");
            button.setBackgroundColor(Color.RED);
            button.setTextColor(Color.WHITE);
            windowManager.addView(button, layoutParams);
            button.setOnTouchListener(new FloatingOnTouchListener());
            button.setOnClickListener((View view) -> {
                click();
            });
        }
    }

    public void click() {
        if (type) {
            type = false;
            button.setText("已开启");
            timer.start();
            button.setBackgroundColor(Color.BLUE);
        } else {
            stop("", 0);
        }
    }


    @RegisterRxBus(1)
    private void stop(String s, int tag) {
        L.e("收到RXbus，关闭");
        type = true;
        button.setText("已关闭");
        timer.cancel();
        button.setBackgroundColor(Color.RED);
    }


    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
        tm.listen(listener, PhoneStateListener.LISTEN_NONE);
        Aria.upload(this).unRegister();
        listener = null;
        timer = null;
    }

    public interface contralListener {

        void start(String number);

        void stop();
    }

    private void initWindow() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = 200;
        layoutParams.height = 200;
        layoutParams.x = 0;
        layoutParams.y = 600;
    }
}
