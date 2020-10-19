package com.smh.szyproject.test.jetpack.bilibiliJetPack.dataBindForLunzi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hjq.http.listener.OnHttpListener;
import com.smh.szyproject.R;
import com.smh.szyproject.databinding.TestDatabindMyactivity2Binding;
import com.smh.szyproject.databinding.TestDatabindMyactivityBinding;
import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.other.utils.L;

import java.util.List;

/**
 * author : smh
 * date   : 2020/9/29 16:48
 * desc   :
 */
public class MyActivity extends AppCompatActivity implements OnHttpListener {
    ViewModel2 viewModel;
    TestDatabindMyactivity2Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_databind_myactivity2);
        viewModel = new ViewModelProvider(this).get(ViewModel2.class);
        binding.setData(viewModel);//这个data就是xml中的name
        binding.setLifecycleOwner(this);//在这里添加生命周期的观测

        viewModel.getUsers(this).observe(this, (List<Test> tests) -> {
            L.e("数据发生改变");
            for (Test test : tests) {
                L.e("" + test.getMsg());
            }
        });

        viewModel.getUsers(this,this).observe(this, (List<Test> tests) -> {
            L.e("数据发生改变");
            for (Test test : tests) {
                L.e("" + test.getMsg());
            }
        });
    }

    @Override
    public void onSucceed(Object result) {

    }

    @Override
    public void onFail(Exception e) {

    }
}
