package com.smh.szyproject.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.smh.szyproject.R;
import com.smh.szyproject.db.DBHelper;
import com.smh.szyproject.ui.adapter.ZmContactAdapter;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.base.CommonAdapter;
import com.smh.szyproject.bean.ZmContact;
import com.smh.szyproject.ui.dialog.ZMAddContactDialog;
import com.smh.szyproject.utils.GsonUtils;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/4/27 10:23
 * desc   :
 */
public class ZMactivity extends BaseActivity implements View.OnClickListener, ZmContactAdapter.zmListener, ZMAddContactDialog.addListener {

    @BindView(R.id.tv_et)
    EditText tvET;
    @BindView(R.id.iv_test_search)
    ImageView ivSearch;
    @BindView(R.id.rv_zm_contact)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout smartRefreshLayout;

    ZmContactAdapter adapter;
    ZMAddContactDialog addContactDialog;
    List<ZmContact> list;
    private ClipData myClip;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    @Override
    public int getLayoutId() {
        return R.layout.zm_activity;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initDate();
    }

    private void initDate() {
        list = DBHelper.getInstance().findAll(ZmContact.class);
        adapter = new ZmContactAdapter(list, this, this);
        adapter.setOnLongClickLinster(new CommonAdapter.OnItemLongClickLinster() {
            @Override
            public void onItemClick(int position, View view) {
                L.e("我是第:" + position + "个");
                ZmContact contact = list.get(position);
                StringBuffer sb = new StringBuffer();
                sb.append(contact.getName() + " ");
                sb.append(contact.getPhone() + " ");
                sb.append(contact.getAddress());
                String text = sb.toString();//复制邀请码
                ClipboardManager myClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showToastForText(ZMactivity.this, "复制成功");

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        smartRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_light,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tvET.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list = DBHelper.getInstance().findAll(ZmContact.class);
                        adapter.refresh(list);
                        smartRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @OnClick({R.id.iv_test_search, R.id.tv_add})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_test_search:
                L.e("点击搜索");
                String etName = tvET.getText().toString().trim();
                if (!TextUtils.isEmpty(etName)) {
                    List<ZmContact> newList = (List<ZmContact>) DBHelper.getInstance().findByLike(ZmContact.class, "name", etName);
                    list.clear();
                    if (newList != null && newList.size() > 0) {
                        for (ZmContact info : newList) {
                            list.add(info);
                        }
                    }
                    adapter.refresh(list);
                } else {
                    if (list == null) return;
                    list.clear();
                    list = DBHelper.getInstance().findAll(ZmContact.class);
                    adapter.refresh(list);
                }
                break;
            case R.id.tv_add:
                addContactDialog = new ZMAddContactDialog(ZMactivity.this, R.style.dialog_style, this);
                addContactDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void remove(int position) {
        list.clear();
        list = DBHelper.getInstance().findAll(ZmContact.class);
        adapter.refresh(list);
    }

    @Override
    public void add(ZmContact contact) {
        DBHelper.getInstance().save(contact);
        list.clear();
        list = DBHelper.getInstance().findAll(ZmContact.class);
        adapter.refresh(list);
    }


    @Override
    public void addCommit(ZmContact contact) {
        addContactDialog.dismiss();
        DBHelper.getInstance().save(contact);
        list.clear();
        list = DBHelper.getInstance().findAll(ZmContact.class);
        adapter.refresh(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBHelper.getInstance().close();
    }
}
