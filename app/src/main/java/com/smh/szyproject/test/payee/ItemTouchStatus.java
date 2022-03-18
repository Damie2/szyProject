package com.smh.szyproject.test.payee;


import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchStatus {
    boolean onItemRemove(int position);

    void onSaveItemStatus(RecyclerView.ViewHolder viewHolder);
}