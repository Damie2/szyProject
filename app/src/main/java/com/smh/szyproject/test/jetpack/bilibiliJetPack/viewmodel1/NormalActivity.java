package com.smh.szyproject.test.jetpack.bilibiliJetPack.viewmodel1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/22 13:48
 * desc   :普通的activity方法
 *
 * jetpack
 * https://www.bilibili.com/video/av90547780/
 */
public class NormalActivity extends BaseActivity {

    @BindView(R.id.textView1)
    public TextView textView1;
    @BindView(R.id.textView2)
    public TextView textView2;

    private int tv1,tv2;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_viewmodel_normal;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        textView1.setText(String.valueOf(tv1));
        textView2.setText(String.valueOf(tv2));
        findViewById(R.id.button1).setOnClickListener((View v)->{
            tv1 ++;
            textView1.setText(String.valueOf(tv1));

        });
        findViewById(R.id.button2).setOnClickListener((View v)->{
            tv2 ++;
            textView2.setText(String.valueOf(tv2));
        });
    }
}
