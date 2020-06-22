package com.smh.szyproject.test.socket;

import android.os.Bundle;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/16 11:19
 * desc   :
 */
public class SendActivity extends BaseActivity {
    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        String msg = "哈哈哈哈";
        textView.setOnClickListener(v -> {new Thread(() -> {new Client(msg).send();}).start();});
    }
}
