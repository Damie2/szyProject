package com.smh.szyproject.test.payee;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;

import java.util.List;

/**
 * @Author smh
 * @Date 2022/3/2 16:20
 */
public class PayeeAdapter extends CommonAdapter<String> implements ItemTouchStatus {
    public PayeeAdapter(List<String> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.payee_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, String s) {

    }

    @Override
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {

    }
}
