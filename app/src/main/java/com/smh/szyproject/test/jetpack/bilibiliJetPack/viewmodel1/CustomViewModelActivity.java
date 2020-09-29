package com.smh.szyproject.test.jetpack.bilibiliJetPack.viewmodel1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/27 11:02
 * desc   : 第二步 ，创建一个viewActivity
 */
public class CustomViewModelActivity extends BaseActivity {
    private CustomViewModel viewModel;
    @BindView(R.id.textView1)
    public TextView textView1;
    @BindView(R.id.textView2)
    public TextView textView2;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_viewmodel_normal;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //通过ViewModelProvider来获取viewModel
        viewModel = new ViewModelProvider(this).get(CustomViewModel.class);
        textView1.setText(String.valueOf(viewModel.getTv1()));
        textView2.setText(String.valueOf(viewModel.getTv2()));

        findViewById(R.id.button1).setOnClickListener((View v)->{
            viewModel.setTv1(viewModel.getTv1()+1);
            textView1.setText(String.valueOf(viewModel.getTv1()));

        });
        findViewById(R.id.button2).setOnClickListener((View v)->{
            viewModel.setTv2(viewModel.getTv2()+1);
            textView2.setText(String.valueOf(viewModel.getTv2()));
        });
    }
}
