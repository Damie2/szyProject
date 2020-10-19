package com.smh.szyproject.test.jetpack.bilibiliJetPack.myJetPack;

import android.content.Context;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.mvp.bean.ZmContact;

import java.util.List;

/**
 * author : smh
 * date   : 2020/4/27 11:14
 * desc   :
 */
public class ZmContactAdapter extends CommonAdapter<ZmContact> {

    private Context context;

    public ZmContactAdapter(List<ZmContact> datas, Context context) {
        super(datas, context);
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_zm_contact_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, ZmContact zmContact) {
        TextView tvName = holder.findViewById(R.id.tv_name);
        TextView tvPhone = holder.findViewById(R.id.tv_phone);
        TextView tvAddress = holder.findViewById(R.id.tv_address);

        tvName.setText(zmContact.getName());
        tvPhone.setText(zmContact.getPhone());
        tvAddress.setText(zmContact.getAddress());
    }
}
