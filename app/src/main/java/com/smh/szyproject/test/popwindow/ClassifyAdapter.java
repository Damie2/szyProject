package com.smh.szyproject.test.popwindow;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;


import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;

import java.util.List;

public class ClassifyAdapter extends CommonAdapter<String> {
    private int selectPosiion=-1;

    public int getSelectPosiion() {
        return selectPosiion;
    }

    public void setSelectPosiion(int selectPosiion) {
        this.selectPosiion = selectPosiion;
    }

    public ClassifyAdapter(List<String> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_item_classify;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, String o) {
        TextView tv_classify = holder.findViewById(R.id.tv_classify);
        tv_classify.setText(o);
        if (selectPosiion==position){
            tv_classify.setTextColor(Color.parseColor("#fd3535"));
        }else {
            tv_classify.setTextColor(Color.parseColor("#36373d"));
        }
    }
}
