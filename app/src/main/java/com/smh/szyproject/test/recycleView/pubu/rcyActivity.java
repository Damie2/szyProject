package com.smh.szyproject.test.recycleView.pubu;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/12/1 13:35
 * desc   :
 */
public class rcyActivity extends BaseActivity {

    private List<Bean> list;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    CashWinthdrawalAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.test_cash_withdrawal;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initDate();
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new CashWinthdrawalAdapter(list, this);
        adapter.getRandomHeight(list);
        recyclerView.setAdapter(adapter);
    }

    private void initDate() {
       list = new ArrayList<>();
        Bean b;
        for (int i = 0; i < 20; i++) {
            b = new Bean();
            b.setUrl("http://f.hiphotos.baidu.com/image/pic/item/dbb44aed2e738bd4f60e4017a48b87d6277ff9ed.jpg");
            list.add(b);
        }
    }
}
