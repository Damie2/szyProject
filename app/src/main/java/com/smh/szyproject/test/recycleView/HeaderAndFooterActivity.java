package com.smh.szyproject.test.recycleView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.mvp.bean.ZmContact;
import com.smh.szyproject.other.db.DBHelper;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.zm1.dialog.ZMAddContactDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/11/13 17:34
 * desc   :
 */
public class HeaderAndFooterActivity extends BaseActivity {
    @BindView(R.id.tv_et)
    EditText tvET;
    @BindView(R.id.iv_test_search)
    ImageView ivSearch;
    @BindView(R.id.rv_zm_contact)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout smartRefreshLayout;

    headFootAdapter adapter;
    List<HeadAndFootBean> list;
    private ClipData myClip;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    @BindView(R.id.header)
    TextView tv_header;

    @Override
    public int getLayoutId() {
        return R.layout.test_zm_activity;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        smartRefreshLayout.setEnabled(false);
        initDate();

        int i = 1/0;
    }

    private void initDate() {
        list = new ArrayList<>();
        //头
        HeadAndFootBean contact;
        contact = new HeadAndFootBean();
        contact.setType(0);
        contact.setAddress("地址:");
        contact.setName("名字");
        contact.setPhone("电话");
        list.add(contact);

        for (int i = 0; i < 10; i++) {
            contact = new HeadAndFootBean();
            contact.setAddress("地址:" + i);
            contact.setName("名字" + i);
            contact.setPhone("电话" + i);
            contact.setType(2);
            list.add(contact);
        }


        //尾巴
        HeadAndFootBean contact1 = new HeadAndFootBean();
        contact1.setType(1);
        contact1.setAddress("地址:");
        contact1.setName("名字");
        contact1.setPhone("电话");
        list.add(contact1);

        adapter = new headFootAdapter(list, HeaderAndFooterActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBHelper.getInstance().close();
    }
}
