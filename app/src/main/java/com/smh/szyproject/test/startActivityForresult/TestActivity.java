package com.smh.szyproject.test.startActivityForresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.ChooseVideoActivity;

/**
 * @Author smh
 * @Date 2021/11/24 9:32
 */
public class TestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_camera_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        onActivityResult过时了,换这个
        findViewById(R.id.btn_start).setOnClickListener(v -> {
            ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //此处是跳转的result回调方法
                    if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                        result.getData().getStringExtra("extra");
                    } else {
                        showToast("sss");
                    }
                }
            });
            Intent intent = new Intent(this, ChooseVideoActivity.class);
            intentActivityResultLauncher.launch(intent);
        });
    }
}
