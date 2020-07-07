package com.smh.szyproject.test.notification;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.azhon.appupdate.utils.NotificationUtil;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.NotificationHelp;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/7/3 10:32
 * desc   :
 */
public class NotificationActivity extends BaseActivity {

    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }


    @Override
    public void init(Bundle savedInstanceState) {
        //仅仅只是展示出来，必须调用cancelNotification才能取消
//        first();
//        second();

        textView.setOnClickListener((view) -> {
            //可以展示，点击并且跳转
            second();
        });
    }

    private void second() {
        //注意，如果没有activity的话intent需要设置flag
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.smh.szyproject", "com.smh.szyproject.ui.activity.AboutActivity"));
        NotificationHelp.getInstance(this).createNotification(this,"我是title","我是内容",intent,R.drawable.ic_launcher);



//        参考这个
//        if (NotificationHelp.getInstance().isBackground()) {
//            int size = MyApplication.getInstance().activities.size();
//
//
//            Intent intent = new Intent();
//            intent.setComponent(new ComponentName("com.nine.stock", "com.nine.stock.MainActivity"));
//            intent.putExtra("position", 1);
//            if (size == 0) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            } else {
//                context = MyApplication.getInstance().activities.get(size - 1);
//            }
    }

    //仅仅只是展示出来，必须调用cancelNotification才能取消
    private void first() {
        textView.setOnClickListener((view) -> {
            //仅仅只是展示出来，没法去掉
            NotificationUtil.showNotification(this, R.drawable.ic_launcher, "我是内容", "contentaaaaaaa");
        });
        textView.postDelayed(() -> {
            NotificationUtil.cancelNotification(this);
        }, 5000);

    }
}
