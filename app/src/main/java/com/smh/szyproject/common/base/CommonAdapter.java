package com.smh.szyproject.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2018/5/25.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private List<T> datas;
    protected Context context;
    private OnItemClickLinster linster;
    private OnItemLongClickLinster longClicklinster;

    public void setOnClickLinster(OnItemClickLinster linster) {
        this.linster = linster;
    }

    public void setOnLongClickLinster(OnItemLongClickLinster linster) {
        this.longClicklinster = linster;
    }

    public List<T> getDatas() {
        return datas;
    }

    public CommonAdapter setDatas(List<T> datas) {
        if (this.datas != null)
            this.datas.addAll(datas);
        else
            this.datas = datas;
        return this;
    }

    public void clearData() {
        this.datas = null;
    }

    public CommonAdapter(List<T> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayoutId(viewType), parent, false);
        return new ViewHolder(view, viewType);
    }

    protected abstract int getLayoutId(int viewType);


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linster != null)
                    linster.onItemClick(position, v);
            }
        });
        holder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClicklinster != null) {
                    longClicklinster.onItemClick(position, v);
                    return true;
                }
                return false;

            }
        });
        if (getItem(position) != null)
            showItemContent(holder, position, getItem(position));
    }

    public T getItem(int position) {
        return datas.get(position);
    }

    protected abstract void showItemContent(ViewHolder holder, int position, T t);


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void refresh(List list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.datas = list;
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        datas.remove(position);
        this.notifyDataSetChanged();
    }

    public interface OnItemClickLinster {
        void onItemClick(int position, View view);
    }

    public interface OnItemLongClickLinster {
        void onItemClick(int position, View view);
    }
}
