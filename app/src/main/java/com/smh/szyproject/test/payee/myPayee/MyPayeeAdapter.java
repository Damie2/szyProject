package com.smh.szyproject.test.payee.myPayee;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.payee.ItemTouchStatus;
import com.smh.szyproject.test.payee.payeeGit.SortModel;

import java.util.List;

/**
 * @Author smh
 * @Date 2022/3/2 16:20
 */
public class MyPayeeAdapter extends CommonAdapter<SortModel> implements ItemTouchStatus {
    private List<SortModel> mData;

    ImageView iv_icon;
    TextView tv_name, tv_null, tv_delete, tv_edit, tvTag, tv_msg;
    LinearLayout ll_info;
    Context context;


    public MyPayeeAdapter(List<SortModel> datas, Context context) {
        super(datas, context);
        this.mData = datas;
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.payee_item_my;
//        return R.layout.item_view;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, SortModel s) {

//        TextView textView = holder.findViewById(R.id.delete);
//        textView.setOnClickListener(v->{
//            getDatas().remove(s);
//            notifyItemRemoved(position);
//        });
        iv_icon = holder.findViewById(R.id.iv_icon);
        tv_name = holder.findViewById(R.id.tv_name);
        tv_msg = holder.findViewById(R.id.tv_msg);
        ll_info = holder.findViewById(R.id.ll_info);
        tv_null = holder.findViewById(R.id.tv_null);
        tv_edit = holder.findViewById(R.id.tv_edit);
        tv_delete = holder.findViewById(R.id.tv_delete);
        tvTag = holder.findViewById(R.id.tag);
        tv_null.setVisibility(View.GONE);
        tv_name.setText(s.getName());
//        Glide.with(context).load(R.drawable.ic_launcher).transform(new GlideCircleTransform(context)).into(iv_icon);
        Glide.with(context).load(R.drawable.ic_launcher).into(iv_icon);
        tv_edit.setOnClickListener(v -> {
            L.e("点击编辑");
        });


        tv_delete.setOnClickListener(v -> {
            L.e("点击删除");
            getDatas().remove(s);
            notifyItemRemoved(position);
        });

        tv_msg.setText("" + position);
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            tvTag.setVisibility(View.VISIBLE);
            tvTag.setText(mData.get(position).getLetters());
        } else {
            tvTag.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {

    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 提供给Activity刷新数据
     *
     * @param list
     */
    public void updateList(List<SortModel> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return mData.get(position).getLetters().charAt(0);
    }
}
