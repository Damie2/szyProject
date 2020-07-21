package com.smh.szyproject.test.zmdatamanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.GsonUtils;
import com.smh.szyproject.test.zmdatamanager.bean.Product;
import com.smh.szyproject.test.zmdatamanager.activity.DetailActivity;

import java.util.List;

/**
 * author : smh
 * date   : 2020/7/15 11:06
 * desc   :
 */
public class ProductAdapter extends CommonAdapter<Product> {
    Context context;

    public ProductAdapter(List<Product> datas, Context context) {
        super(datas, context);
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_product_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Product product) {

        TextView tvName = holder.findViewById(R.id.tv_product_name);
        TextView tvGeneralAgencyPrice = holder.findViewById(R.id.tv_general_agency_price);
        TextView tvAgentPrice = holder.findViewById(R.id.tv_agent_price);
        TextView tvRetail = holder.findViewById(R.id.tv_retail);
        TextView tvExtra = holder.findViewById(R.id.tv_extra);
        TextView tvEdit = holder.findViewById(R.id.tv_edit);

        tvName.setText(product.getName());
        tvGeneralAgencyPrice.setText(product.getGeneralAgencyPrice()+"");
        tvAgentPrice.setText(product.getAgentPrice()+"");
        tvRetail.setText(product.getRetail()+"");
        tvExtra.setText(product.getExtra());
        tvEdit.setOnClickListener((view) -> {
            Intent  intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product", GsonUtils.getGson().toJson(product));
            context.startActivity(intent);
        });

    }
}
