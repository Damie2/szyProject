package com.smh.szyproject.test.mvvm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import com.smh.szyproject.R;
import com.smh.szyproject.databinding.ActivityMvvmBinding;

import butterknife.BindView;

public class MvvmActivity extends AppCompatActivity {
    NotificationManager manager;
    @BindView(R.id.iv)
    ImageView iv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding activityMvvmBinding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);
        User user = new User("321","123");
        activityMvvmBinding.setUser(user);
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            //只在Android O之上需要渠道
            NotificationChannel notificationChannel = new NotificationChannel("channelid1","channelname",NotificationManager.IMPORTANCE_HIGH);
            //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道，通知才能正常弹出
            manager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channelid1");
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("通知标题")
                .setContentText("通知内容")
                .setAutoCancel(true);
        manager.notify(0x12,builder.build());


//        ImageLoader.with(this).load("http://img14.360buyimg.com/n1/jfs/t28306/211/882124028/609313/1947379a/5bffa0a4N1903d27d.png").circle().into(iv);

    }

    }


