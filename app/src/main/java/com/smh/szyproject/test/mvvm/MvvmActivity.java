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
        ActivityMvvmBinding activityMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        User user = new User("321", "123");
        activityMvvmBinding.setUser(user);
    }
}


