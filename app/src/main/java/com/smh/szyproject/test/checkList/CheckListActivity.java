package com.smh.szyproject.test.checkList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/8/18 9:26
 * desc   :
 */
public class CheckListActivity extends BaseActivity implements View.OnClickListener {
    List<String> list;//数据源
    List<String> listData;//记录选择的数据
    List<String> submitListData;//记录选择的数据
    private boolean isSelect = false;//全选按钮的状态
    CheckItemAdapter adapter;
    @BindView(R.id.rv_check_list)
    public RecyclerView recyclerView;
    @BindView(R.id.bar)
    public TitleBar bar;

    @Override
    public int getLayoutId() {
        return R.layout.test_check_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.setTitleBar(this, bar);
        list = new ArrayList<>();
        listData = new ArrayList<>();
        submitListData = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            list.add(i + "");
        }

        adapter = new CheckItemAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onLeftClick(View v) {
        if (!isSelect) {
            isSelect = true;//全选
            adapter.All();
            setLeftTitle("取消全选");
        } else {
            isSelect = false;//取消全选
            adapter.neverAll();
            setLeftTitle("全选");
        }
    }

    @Override
    public void onRightClick(View v) {
        Map<Integer, Boolean> map = adapter.getMap();
        for (int i = 0; i < list.size(); i++) {
            if (map.get(i)){
                listData.add(list.get(i));
            }
        }

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String msg  = iterator.next();
            for(String info:listData){
                if(TextUtils.equals(info,msg)){
                    iterator.remove();
                }
            }
        }
        //点删除之后，状态改成未选中
        adapter.changeMapStatus();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTitleClick(View v) {
        showToast("点title");
    }

    @OnClick(R.id.tv_submit)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String  content="";
        submitListData.clear();
        Map<Integer, Boolean> map = adapter.getMap();
        for (int i = 0; i < list.size(); i++) {
            if (map.get(i)){
                submitListData.add(list.get(i));
            }
        }
        for (int j = 0; j <submitListData.size() ; j++) {
            content+=submitListData.get(j)+",";
        }
        L.e("内容是: "+content);
    }
}
