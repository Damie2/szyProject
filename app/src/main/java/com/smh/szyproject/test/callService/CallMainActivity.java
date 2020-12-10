package com.smh.szyproject.test.callService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

import android.widget.Button;

import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;

import com.smh.szyproject.other.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/22 15:12
 * desc   : 使用bind来启动service
 */
public class CallMainActivity extends BaseActivity implements ControlWindowService.contralListener {
    @BindView(R.id.connect)
    Button connect;
    ControlWindowService mMyService;

    @BindView(R.id.disconnect)
    Button disconnect;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_call;
    }

    @Permissions({Permission.MANAGE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE, Permission.CALL_PHONE, Permission.RECORD_AUDIO})
    @Override
    public void init(Bundle savedInstanceState) {
        Intent bindIntent = new Intent(CallMainActivity.this, ControlWindowService.class);
        bindService(bindIntent, sconnection, Context.BIND_AUTO_CREATE);
        disconnect.setOnClickListener(v->{ call();});
    }

    /* 绑定service监听*/
    ServiceConnection sconnection = new ServiceConnection() {
        /*当绑定时执行*/
        public void onServiceConnected(ComponentName name, IBinder service) {  //service的onbind（）中返回值不为null才会触发
            mMyService = ((ControlWindowService.MyBinder) service).getService();//得到该service实例
            mMyService.setCallback(CallMainActivity.this, CallMainActivity.this);//把回调对象传送给service
        }

        /*当异常结束service时执行，但调用unbindService()时不会触发改方法 测试的话可以在bind时使用Context.BIND_NOT_FOREGROUND  调用stopservice（）可触发*/
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Permissions({Permission.CALL_PHONE})
    public void call(){
        L.e("走了？");
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }


    @Permissions({Permission.CALL_PHONE,Permission.ANSWER_PHONE_CALLS})
    @Override
    public void start(String number) {
        callPhone(number);
    }

    @Override
    public void stop() {
    }

    //请求完了之后，打电话
    private void callPhone(String number) {
        L.e("打电话?");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(sconnection);//解绑下service否则  退出会报错
    }
}
