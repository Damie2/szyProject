package com.smh.szyproject.test.payee.OutPayee;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.ToastUtils;
import com.smh.szyproject.test.payee.ItemTouchStatus;
import com.smh.szyproject.test.payee.mobilePayee.LetterModel;
import com.smh.szyproject.test.payee.payeeGit.SortModel;

import java.util.List;

/**
 * @Author smh
 * @Date 2022/3/4 14:05
 */
public class OutPayAdapter extends CommonAdapter<SortModel> implements ItemTouchStatus {
    List<SortModel> mData;
    Context context;

    public OutPayAdapter(List<SortModel> datas, Context context) {
        super(datas, context);
        this.context = context;
        this.mData = datas;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, SortModel sortModel) {
        switch (getItemViewType(position)) {
            case 0://标题类型
                TextView tvTag = holder.findViewById(R.id.tag);
                tvTag.setText(sortModel.getLetters());
                break;
            case 1://普通类型
                ImageView iv_icon = holder.findViewById(R.id.iv_icon);
                TextView tv_name = holder.findViewById(R.id.tv_name);
                TextView tv_msg = holder.findViewById(R.id.tv_msg);
                TextView tv_null = holder.findViewById(R.id.tv_null);
                TextView tv_edit = holder.findViewById(R.id.tv_edit);
                TextView tv_delete = holder.findViewById(R.id.tv_delete);
                tv_null.setVisibility(View.GONE);
                Glide.with(context).load(R.drawable.ic_launcher).into(iv_icon);
                tv_msg.setText(position + "");
                tv_name.setText(sortModel.getName());

                tv_delete.setOnClickListener(v -> {
                    SortModel newModel = getDatas().get(position);
                    getDatas().remove(position);
                    queryData(newModel, getDatas(), position);
                    holder.getView().scrollTo(0, 0);
                    refresh(getDatas());
                });

                tv_edit.setOnClickListener(v -> {
                    ToastUtils.showToastForText(context, "点击编辑");
                });
                break;
        }
    }

    private void queryData(SortModel newModel, List<SortModel> datas, int position) {
        String letters = newModel.getLetters();
        int i = 0;
        for (SortModel m : datas) {
            if (TextUtils.equals(letters, m.getLetters())) {
                i++;
            }
        }
        if (i == 1) {
            getDatas().remove(position - 1);
        }
    }

    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:
                return R.layout.payee_item_tag;
            case 1:
                return R.layout.payee_item;
        }
        return R.layout.payee_item;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getType() == 0) {
            return 0;
        } else if (getItem(position).getType() == 1) {
            return 1;
        }
        return -1;
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


    @Override
    public boolean onItemRemove(int position) {
        return false;
    }

    @Override
    public void onSaveItemStatus(RecyclerView.ViewHolder viewHolder) {

    }
}
