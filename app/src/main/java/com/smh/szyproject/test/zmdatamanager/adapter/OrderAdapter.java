package com.smh.szyproject.test.zmdatamanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.common.base.ViewHolder;
import com.smh.szyproject.other.utils.GsonUtils;
import com.smh.szyproject.test.zmdatamanager.activity.DetailActivity;
import com.smh.szyproject.test.zmdatamanager.bean.Order;
import com.smh.szyproject.test.zmdatamanager.bean.Product;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * author : smh
 * date   : 2020/7/15 11:06
 * desc   :
 */
public class OrderAdapter extends CommonAdapter<Order> {
    Context context;

    public OrderAdapter(List<Order> datas, Context context) {
        super(datas, context);
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.test_order_item;
    }

    @Override
    protected void showItemContent(ViewHolder holder, int position, Order product) {

        TextView tvName = holder.findViewById(R.id.tv_product_name);
        TextView tvNumber = holder.findViewById(R.id.tv_number);
        TextView tvStatus = holder.findViewById(R.id.tv_status);
        TextView tvDate = holder.findViewById(R.id.tv_date);
        TextView tvExtra = holder.findViewById(R.id.tv_extra);
        TextView tvEdit = holder.findViewById(R.id.tv_edit);
        TextView tvSalesVolume = holder.findViewById(R.id.tv_sales_volume);//流水
        TextView tvSalesProfit = holder.findViewById(R.id.tv_sales_profit);//利润

        tvName.setText(product.getName());
        tvNumber.setText(product.getNumber() + "");
        float singleSalesProfit = 0L;
        switch (product.getStatus()) {
            case 0:
                tvStatus.setText("代理");
                //如果是代理销售 等于特代价格减去总代价格
                singleSalesProfit = product.getAgentPrice() - product.getGeneralAgencyPrice();
                break;
            case 1:
                tvStatus.setText("总代");
                //如果是总代销售 等于零售价格减去总代价格
                singleSalesProfit = product.getRetail() - product.getGeneralAgencyPrice();
                break;
            case 2:
                tvStatus.setText("自吃");
                //如果是自吃，直接负数
                singleSalesProfit = -product.getGeneralAgencyPrice();
                break;
        }

        float salesVolume = product.getNumber() * product.getRetail();
        tvSalesVolume.setText(salesVolume + "");//流水
        //利润
        float salesProfit = product.getNumber() * singleSalesProfit;

        tvSalesProfit.setText(salesProfit + "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        tvDate.setText(simpleDateFormat.format(product.getCreateTime()));
        tvExtra.setText(product.getExtra());
        tvEdit.setOnClickListener((view) -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("product", GsonUtils.getGson().toJson(product));
            context.startActivity(intent);
        });

    }
}
