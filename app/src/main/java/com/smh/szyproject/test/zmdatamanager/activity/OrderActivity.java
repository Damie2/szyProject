package com.smh.szyproject.test.zmdatamanager.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.other.db.DBHelper;
import com.smh.szyproject.other.utils.GsonUtils;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.widget.RegexEditText;
import com.smh.szyproject.test.zmdatamanager.bean.Order;
import com.smh.szyproject.test.zmdatamanager.bean.Product;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/20 16:04
 * desc   :
 */
public class OrderActivity extends BaseActivity implements View.OnClickListener {
    Product product;
    Order order;
    @BindView(R.id.btn_order_delete)
    AppCompatButton delete;
    int status = 0;
    @BindView(R.id.et_name)
    RegexEditText et_submit;//提交人
    @BindView(R.id.et_customer)
    AppCompatEditText et_customer;//顾客
    @BindView(R.id.et_order_extra)
    AppCompatEditText et_extra;//备注

    @BindView(R.id.et_number)
    AppCompatEditText etNumber;//数量


    @Override
    public int getLayoutId() {
        return R.layout.test_order;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        delete.setVisibility(View.INVISIBLE);
        product = GsonUtils.getGson().fromJson(getIntent().getStringExtra("order"), Product.class);
        if (product != null) {
            initData();
        } else {
            showToast("出问题了啊");
        }
    }

    private void initData() {
        RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_agent:
                        //代理销售
                        status = 0;
                        L.e("代理销售");
                        break;
                    case R.id.rb_rose:
                        //总代销售
                        status = 1;
                        L.e("总代销售");
                        break;
                    case R.id.rb_eat:
                        //自吃
                        status = 2;
                        L.e("自吃");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.btn_order_commit)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_order_commit) {
            L.e("提交");
            String submit = et_submit.getText().toString().trim();
            String customer = et_customer.getText().toString().trim();//顾客名称
            String number =etNumber.getText().toString().trim();
            String extra =  et_extra.getText().toString().trim();
            if(!TextUtils.isEmpty(submit)&&!TextUtils.isEmpty(customer)&&!TextUtils.isEmpty(number)){
                order = new Order();
                order.setExtra(extra);
                order.setName(product.getName());
                order.setProductId(product.getId());
                order.setSubmitPerson(submit);
                order.setCustomer(customer);
                order.setCreateTime(System.currentTimeMillis());
                order.setStatus(status);
                order.setNumber(Integer.parseInt(number));
                order.setGeneralAgencyPrice(product.getGeneralAgencyPrice());
                order.setAgentPrice(product.getAgentPrice());
                order.setRetail(product.getRetail());
                DBHelper.getInstance().save(order);
                showToast("提交完成");
                RxBus.getInstance().send("",101);
                finish();
            }else{
            showToast("信息没提交完呢");
            }
        }
    }
}
