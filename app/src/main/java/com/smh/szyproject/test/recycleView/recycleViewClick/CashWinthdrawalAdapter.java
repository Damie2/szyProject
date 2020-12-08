package com.smh.szyproject.test.recycleView.recycleViewClick;

import android.content.Context;

import android.widget.RelativeLayout;
import android.widget.TextView;


import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;

import java.util.List;

public class CashWinthdrawalAdapter extends CommonAdapter<Integer> {
    private int selectPosiion = -1;


    public CashWinthdrawalAdapter(List<Integer> datas, Context context) {
        super(datas, context);
    }

    public int getSelectPosiion() {
        return selectPosiion;
    }

    public void setSelectPosition(int position) {
        selectPosiion = position;
    }


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_cash_winthdrawal_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Integer integer) {
        TextView tv_cash_item_money = holder.findViewById(R.id.tv_cash_item_money);
        tv_cash_item_money.setText(integer + "");

        RelativeLayout root = holder.findViewById(R.id.cash_rl);
        if (selectPosiion == position) {
            root.setBackgroundResource(R.drawable.et_focused);
        } else {
            root.setBackgroundResource(R.drawable.et_normal);
        }
    }
}
