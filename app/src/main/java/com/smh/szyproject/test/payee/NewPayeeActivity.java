package com.smh.szyproject.test.payee;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.test.mvvmGit.architecture.utils.DisplayUtils;
import com.smh.szyproject.ui.view.MyLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Author smh
 * @Date 2022/3/2 16:18
 */
public class NewPayeeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rv_own_rv)
    RecyclerView recyclerView;

    @BindView(R.id.tv_toggle)
    TextView tv_toggle;
    boolean toggle = true;
    private List<String> ownList;
    private List<String> contactsList;
    private PayeeAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payee_new;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initDate();
        initView();
    }

    private void initDate() {
        contactsList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            contactsList.add(i + "");
        }
    }

    private void initView() {
        tv_toggle.setOnClickListener(this);
        if (toggle) {
        } else {
        }


        adapter = new PayeeAdapter(contactsList, this);
        recyclerView.setLayoutManager(new MyLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.setOnClickLinster((int position, View view) -> {//点击事件
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new GamItemTouchCallback(adapter, DisplayUtils.dp2px(100)));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toggle:
                if (!toggle) {
                    toggle = true;
                    tv_toggle.setText("收起");
//                    rvOwn.setVisibility(View.VISIBLE);
                } else {
                    toggle = false;
                    tv_toggle.setText("展示");
//                    rvOwn.setVisibility(View.GONE);
                }
                break;
        }
    }
}