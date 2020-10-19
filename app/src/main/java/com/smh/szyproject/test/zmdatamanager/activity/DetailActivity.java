package com.smh.szyproject.test.zmdatamanager.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.other.db.DBHelper;
import com.smh.szyproject.other.utils.GsonUtils;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.widget.RegexEditText;
import com.smh.szyproject.test.build.CustomDialog;
import com.smh.szyproject.test.build.IDialogView;
import com.smh.szyproject.test.build.OnLeftLinster;
import com.smh.szyproject.test.build.OnRightLinster;
import com.smh.szyproject.test.zmdatamanager.bean.Product;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/20 14:18
 * desc   :
 */
public class DetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_name)
    RegexEditText etName;
    @BindView(R.id.et_general_agency_price)
    AppCompatEditText etGeneralAgencyPrice;
    @BindView(R.id.et_agent_price)
    AppCompatEditText etAgentPrice;
    @BindView(R.id.et_retail)
    AppCompatEditText etRetail;
    @BindView(R.id.et_extra)
    AppCompatEditText etExtra;
    Product product;
    CustomDialog dialog;
    @Override
    public int getLayoutId() {
        return R.layout.test_detail;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        String json = getIntent().getStringExtra("product");
        product = GsonUtils.getGson().fromJson(json, Product.class);
        if (product != null) {
            etName.setText(product.getName());
            etGeneralAgencyPrice.setText(product.getGeneralAgencyPrice() + "");
            etAgentPrice.setText(product.getAgentPrice() + "");
            etRetail.setText(product.getRetail() + "");
            etExtra.setText(product.getExtra());
        }
    }

    @OnClick({R.id.btn_commit,R.id.btn_delete})
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_commit) {
            String name = etName.getText().toString().trim();
            String generalAgencyPrice = etGeneralAgencyPrice.getText().toString().trim();
            String agentPrice = etAgentPrice.getText().toString().trim();
            String retail = etRetail.getText().toString().trim();
            String extra = etExtra.getText().toString().trim();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(generalAgencyPrice) && !TextUtils.isEmpty(agentPrice) && !TextUtils.isEmpty(retail)) {
                product.setExtra(name);
                product.setExtra(extra);

                product.setGeneralAgencyPrice(Float.parseFloat(generalAgencyPrice));
                product.setAgentPrice(Float.parseFloat(agentPrice));
                product.setRetail(Float.parseFloat(retail));
                DBHelper.getInstance().save(product);
                RxBus.getInstance().send("productListChange", 100);
                finish();
            } else {
                showToast("请填写完整信息");
            }
        }else if(view.getId()==R.id.btn_delete){
            dialog = new CustomDialog(this,R.style.dialog_style)
                    .setTitle("是否要删除该商品")
                    .setContent("删完了就没了啊")
                    .setRightBtnText("确定")
                    .setLeftBtnText("取消")
                    .setRightBtnColor(Color.BLUE)
                    .setLeftListener(new OnLeftLinster() {
                        @Override
                        public void onClick(IDialogView view) {
                            L.e("点击左边");
                            dialog.dismiss();
                        }
                    }).setRightListener(new OnRightLinster() {
                        @Override
                        public void onClick(IDialogView view) {
                            L.e("点击右边");
                            if(product!=null){
                                DBHelper.getInstance().deleteById(Product.class,product.getId());
                                showToast("删除成功");
                                RxBus.getInstance().send("productListChange", 100);
                                finish();
                            }else{
                                showToast("你想删啥");
                            }
                            dialog.dismiss();

//                            HihtDialog dialog = (HihtDialog) view;
//                            dialog.dismiss();

                        }
                    });
            dialog.show();
        }
    }
}
