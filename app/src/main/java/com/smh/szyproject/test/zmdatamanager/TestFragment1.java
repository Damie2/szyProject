package com.smh.szyproject.test.zmdatamanager;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.other.Rx.databus.RegisterRxBus;
import com.smh.szyproject.other.db.DBHelper;
import com.smh.szyproject.other.utils.GsonUtils;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.widget.RegexEditText;
import com.smh.szyproject.test.build.CustomDialog;
import com.smh.szyproject.test.build.IDialogView;
import com.smh.szyproject.test.build.OnLeftLinster;
import com.smh.szyproject.test.build.OnRightLinster;
import com.smh.szyproject.test.zmdatamanager.activity.OrderActivity;
import com.smh.szyproject.test.zmdatamanager.adapter.ProductAdapter;
import com.smh.szyproject.test.zmdatamanager.bean.Order;
import com.smh.szyproject.test.zmdatamanager.bean.Product;
import com.smh.szyproject.test.zmdatamanager.activity.DetailActivity;
import com.smh.szyproject.ui.view.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment1 extends BaseFragment {
    List<Product> list;
    ProductAdapter adapter;

    @BindView(R.id.recyclerview)
    RecyclerView rv_data;

    @BindView(R.id.tv_et)
    RegexEditText editText;

    @BindView(R.id.iv_test_search)
    TextView iv_test_search;

    @Override
    protected void init() {
        setRightTitle("新增");
        list = DBHelper.getInstance().findAll(Product.class);
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getContext());
        rv_data.setLayoutManager(mLinearLayout);
        rv_data.addItemDecoration(new SpaceItemDecoration(5));

        adapter = new ProductAdapter(list, getContext());
        rv_data.setAdapter(adapter);
        adapter.setOnClickLinster((int position, View view) -> {
            L.e("进入订单创建页面");
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("order", GsonUtils.getGson().toJson(list.get(position)));
            startActivity(intent);
        });

        adapter.setOnLongClickLinster((int position, View view)->{
            L.e("长按删除");
            new CustomDialog(getContext(), R.style.dialog_style)
                    .setTitle("是否要删除该商品")
                    .setContent("删完了就没了啊")
                    .setRightBtnText("确定")
                    .setLeftBtnText("取消")
                    .setRightBtnColor(Color.BLUE)
                    .setLeftLinster(new OnLeftLinster() {
                        @Override
                        public void onClick(IDialogView view) {
                            L.e("点击左边");
                            CustomDialog dialog = (CustomDialog) view;
                            dialog.dismiss();
                        }
                    }).setRightLinster(new OnRightLinster() {
                @Override
                public void onClick(IDialogView view) {
                    DBHelper.getInstance().deleteById(Product.class,list.get(position).getId());
                    showToast("删除成功");
                    CustomDialog dialog = (CustomDialog) view;
                    dialog.dismiss();
                }
            }).show();
        });

        iv_test_search.setOnClickListener((view) -> {
            L.e("点击搜索");
            String proName = editText.getText().toString().trim();
            if (!TextUtils.isEmpty(proName)) {
                list.clear();
                list = DBHelper.getInstance().findByLike(Product.class, "name", proName);
                adapter.refresh(list);
            } else {
                list = DBHelper.getInstance().findAll(Product.class);
                adapter.refresh(list);
            }
        });
    }

    @Override
    public void onRightClick(View v) {
        startActivity(DetailActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_zm_fragment1;
    }

    //下面是友盟
    @RegisterRxBus(100)
    private void refreshData(String share, int tag) {
        list.clear();
        list = DBHelper.getInstance().findAll(Product.class);
        adapter.refresh(list);
    }
}
