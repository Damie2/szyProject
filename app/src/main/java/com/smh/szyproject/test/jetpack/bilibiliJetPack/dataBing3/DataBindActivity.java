package com.smh.szyproject.test.jetpack.bilibiliJetPack.dataBing3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.smh.szyproject.R;
import com.smh.szyproject.databinding.TestDatabindBilibililBinding;

/**
 * author : smh
 * date   : 2020/9/27 11:51
 * desc   :
 */
public class DataBindActivity extends AppCompatActivity {
    DataBindingViewModel viewModel;

    TestDatabindBilibililBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_databind_bilibilil);
        viewModel = new ViewModelProvider(this).get(DataBindingViewModel.class);
        binding.setData(viewModel);//这个data就是xml中的name
        binding.setLifecycleOwner(this);//在这里添加生命周期的观测
    }
}
