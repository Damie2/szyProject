package com.smh.szyproject.test.mvvm;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
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
        ActivityMvvmBinding activityMvvmBinding = DataBindingUtil.setContentView(this, R.layout.test_activity_mvvm);
        User user = new User("321", "123");
        activityMvvmBinding.setUser(user);
    }
}


