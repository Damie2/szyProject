package com.smh.szyproject.test.jetpack.bilibiliJetPack.myJetPack;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.databinding.TestDatabindMyactivity1Binding;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.bean.ZmContact;
import com.smh.szyproject.other.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/9/30 10:15
 * desc   :
 */
public class MyJetPackActivity extends AppCompatActivity {

    MyViewModel viewModel;
    TestDatabindMyactivity1Binding binding;


    RecyclerView recyclerView;
    ZmContactAdapter adapter;
    List<ZmContact> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_databind_myactivity1);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_zm_contact);
        adapter = new ZmContactAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel.getUsers(this).observe(this, (List<ZmContact> zmContacts) -> {
            for(ZmContact item:zmContacts){
                list.add(item);
            }
            adapter.refresh(list);
        });

        viewModel.getStatus().observe(this,  (ID id) -> {L.e("id是:"+id.getId());});
    }
}
