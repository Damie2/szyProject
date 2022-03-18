package com.smh.szyproject.test.payee;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
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
 * @Date 2022/3/1 10:17
 * @des 收款人list
 */
public class PayeeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rv_own_rv)
    RecyclerView rvOwn;
    @BindView(R.id.rv_contacts)
    RecyclerView rvContacts;
    @BindView(R.id.tv_toggle)
    TextView tv_toggle;
    boolean toggle = true;
    private List<String> ownList;
    private List<String> contactsList;
    private OwnAdapter ownAdapter;
    private ContactsAdapter contactsAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payee;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initDate();
        initView();
    }

    private void initDate() {
        ownList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ownList.add(i + "");
        }
        contactsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            contactsList.add(i + "");
        }
    }

    private void initView() {
        tv_toggle.setOnClickListener(this);
        if (toggle) {
            rvOwn.setVisibility(View.VISIBLE);
        } else {
            rvOwn.setVisibility(View.GONE);
        }
        ownAdapter = new OwnAdapter(ownList, this);
        rvOwn.setLayoutManager(new MyLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvOwn.setAdapter(ownAdapter);
        rvOwn.setHasFixedSize(true);
        rvOwn.setNestedScrollingEnabled(false);
        ownAdapter.setOnClickLinster((int position, View view) -> {//点击事件
        });


        contactsAdapter = new ContactsAdapter(contactsList, this);
        rvContacts.setLayoutManager(new MyLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvContacts.setAdapter(contactsAdapter);
        rvContacts.setHasFixedSize(true);
        rvContacts.setNestedScrollingEnabled(false);
        contactsAdapter.setOnClickLinster((int position, View view) -> {//点击事件
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new GamItemTouchCallback(ownAdapter, DisplayUtils.dp2px( 100)));
        itemTouchHelper.attachToRecyclerView(rvOwn);

        ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(new GamItemTouchCallback(contactsAdapter, DisplayUtils.dp2px(100)));
        itemTouchHelper1.attachToRecyclerView(rvContacts);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toggle:
                if (!toggle) {
                    toggle = true;
                    tv_toggle.setText("收起");
                    rvOwn.setVisibility(View.VISIBLE);
                } else {
                    toggle = false;
                    tv_toggle.setText("展示");
                    rvOwn.setVisibility(View.GONE);
                }
                break;
        }
    }
}
