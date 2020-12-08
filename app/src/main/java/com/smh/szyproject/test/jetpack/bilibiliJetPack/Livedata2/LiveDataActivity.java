package com.smh.szyproject.test.jetpack.bilibiliJetPack.Livedata2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/27 11:23
 * desc   :
 */
public class LiveDataActivity extends BaseActivity {
    LiveDataViewModel viewModel;
    @BindView(R.id.textView1)
    public TextView textView1;
    @BindView(R.id.textView2)
    public TextView textView2;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_viewmodel_normal;
    }

    //liveData是利用观察者模式实现属数据改变，而数据也随之改变

    @Override
    public void init(Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);

        viewModel.getCk().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView1.setText(String.valueOf(viewModel.getCk().getValue()));
            }
        });

        viewModel.getCk().observe(this,i->{

        });


        viewModel.getJy().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView2.setText(String.valueOf(viewModel.getJy().getValue()));
            }
        });

        findViewById(R.id.button1).setOnClickListener((View v)->{
            viewModel.addCk();
        });
        findViewById(R.id.button2).setOnClickListener((View v)->{
            viewModel.addJay();
        });
    }
}
