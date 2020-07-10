package com.smh.szyproject.test.fragment.glodBanner;

import android.content.Context;
import android.widget.ImageView;


import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.common.image.ImageLoader;

import java.util.List;

/**
 * Create by smh on 2018/11/26.
 */
public class MemgerCardAdapter extends CommonAdapter<Integer> {

    public MemgerCardAdapter(List<Integer> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_member_card_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Integer integer) {
        ImageLoader.with(context).load(integer).into(holder.findViewById(R.id.iv_card));
    }
}
