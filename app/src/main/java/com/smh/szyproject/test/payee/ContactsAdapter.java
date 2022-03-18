package com.smh.szyproject.test.payee;


import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.L;

import java.util.List;

public class ContactsAdapter extends CommonAdapter<String> implements ItemTouchStatus{
    ImageView iv_icon;
    TextView tv_name;
    TextView tv_msg;
    LinearLayout ll_info;
    TextView tv_null;
    TextView tv_delete;
    TextView tv_edit;
    public ContactsAdapter(List<String> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.payee_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, String s) {
        iv_icon = holder.findViewById(R.id.iv_icon);
        tv_name = holder.findViewById(R.id.tv_name);
        tv_msg = holder.findViewById(R.id.tv_msg);
        ll_info = holder.findViewById(R.id.ll_info);
        tv_null = holder.findViewById(R.id.tv_null);
        tv_edit = holder.findViewById(R.id.tv_edit);
        tv_delete = holder.findViewById(R.id.tv_delete);
        tv_edit = holder.findViewById(R.id.tv_edit);
        tv_delete = holder.findViewById(R.id.tv_delete);
        tv_edit.setOnClickListener(v->{
            L.e("点击编辑");
        });
        tv_delete.setOnClickListener(v->{
            L.e("点击删除");
            getDatas().remove(s);
            notifyItemRemoved(holder.getAdapterPosition());
        });
        tv_null.setVisibility(View.GONE);
//        Glide.with(getActivity()).load(R.drawable.iv_head_man).transform(new GlideCircleTransform(getActivity())).into(iv_head);
        Glide.with(context).load(R.drawable.ic_launcher).into(iv_icon);

    }

    @Override
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {

    }
}
