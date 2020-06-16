package com.smh.szyproject.other.umeng;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.other.utils.L;
import com.umeng.message.UmengNotifyClickActivity;

import org.android.agoo.common.AgooConstants;

/**
 * author : smh
 * date   : 2020/5/11 15:15
 * desc   :点击通知栏会默认启动app，不能跳到指定页面。  三个通道只要创建这一个activity即可  小米、华为和魅族
 * 看这个   com.smh.szyproject.other.umeng.PushActivcity
 * https://blog.csdn.net/qq598535550/article/details/80601844
 */
public class PushActivcity extends UmengNotifyClickActivity {
    private static String TAG = PushActivcity.class.getName();
    private TextView mipushTextView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mipush);
        mipushTextView = (TextView) findViewById(R.id.mipushTextView);
    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);
        final String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        Log.i(TAG, body);
        L.e("body:"+body);
        if (!TextUtils.isEmpty(body)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mipushTextView.setText(body);
                }
            });
        }
    }
}
