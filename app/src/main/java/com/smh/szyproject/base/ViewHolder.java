package com.smh.szyproject.base;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

//import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by android on 2018/5/25.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> Views;
    private View itemView;
    private int vieType;

    public int getVieType() {
        return vieType;
    }

    public ViewHolder(View itemView, int vieType) {
        super(itemView);
        this.vieType = vieType;
        Views = new SparseArray<>();
        this.itemView = itemView;
//        AutoUtils.auto(itemView);
    }

    public <T extends View> T findViewById(int id) {
        T view = (T) Views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            if (view != null)
                Views.put(id, view);
        }

        return view;
    }
    public void  putView(int id, View view){
        Views.put(id, view);
    }
    public String toString(){
        return Views.toString();
    }
    public View getView() {
        return itemView;
    }
}