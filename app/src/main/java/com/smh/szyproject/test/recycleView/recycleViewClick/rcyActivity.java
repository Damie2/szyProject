package com.smh.szyproject.test.recycleView.recycleViewClick;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.ui.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/12/1 13:35
 * desc   :
 */
public class rcyActivity extends BaseActivity {

    private List<Integer> list;

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
        adapter = new CashWinthdrawalAdapter(list, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpaceItemDecoration(15));
        recyclerView.setAdapter(adapter);

        adapter.setOnClickLinster((int position, View view) -> {
                    int old = adapter.getSelectPosiion();
                    adapter.setSelectPosition(position);
                    adapter.notifyItemChanged(position);
                    adapter.notifyItemChanged(old);
                }
        );
    }

    private void initDate() {
        list = new ArrayList();
        for (int i = 0; i < 3000; i++) {
            list.add(i);
        }
    }
}
