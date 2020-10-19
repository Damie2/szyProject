package com.smh.szyproject.test.jetpack.bilibiliJetPack.dataBing3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.smh.szyproject.R;
import com.smh.szyproject.databinding.TestDatabindMyactivityBinding;
import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.other.utils.L;

import java.util.List;

/**
 * author : smh
 * date   : 2020/9/29 16:48
 * desc   :
 */
public class MyActivity extends AppCompatActivity {
    ViewModel1 viewModel;
    TestDatabindMyactivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_databind_myactivity);
        viewModel = new ViewModelProvider(this).get(ViewModel1.class);
        binding.setData(viewModel);//这个data就是xml中的name
        binding.setLifecycleOwner(this);//在这里添加生命周期的观测

        //然后数据添加观察
//        viewModel.getUsers().observe(this, new Observer<List<Test>>() {
//            @Override
//            public void onChanged(List<Test> tests) {
//                L.e("数据发生改变");
//                for (Test test : tests) {
//                    L.e("" + test.getMsg());
//                }
//            }
//        });
        viewModel.getUsers().observe(this, (List<Test> tests) -> {
            L.e("数据发生改变");
            for (Test test : tests) {
                L.e("" + test.getMsg());
            }
        });
    }
}
