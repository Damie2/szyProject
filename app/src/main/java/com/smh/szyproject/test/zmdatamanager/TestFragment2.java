package com.smh.szyproject.test.zmdatamanager;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.other.Rx.databus.RegisterRxBus;
import com.smh.szyproject.other.db.DBHelper;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.build.CustomDialog;
import com.smh.szyproject.test.build.IDialogView;
import com.smh.szyproject.test.build.OnLeftLinster;
import com.smh.szyproject.test.build.OnRightLinster;
import com.smh.szyproject.test.zmdatamanager.adapter.OrderAdapter;
import com.smh.szyproject.test.zmdatamanager.bean.Order;
import com.smh.szyproject.ui.view.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment2 extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView rv_data;
    OrderAdapter adapter;
    List<Order> list;

    @BindView(R.id.sl)
    SmartRefreshLayout refreshLayout;


    @Override
    protected void init() {
        setRightTitle("筛选");
        list = DBHelper.getInstance().findAll(Order.class);
        LinearLayoutManager mLinearLayout = new LinearLayoutManager(getContext());
        rv_data.setLayoutManager(mLinearLayout);
        rv_data.addItemDecoration(new SpaceItemDecoration(5));
        adapter = new OrderAdapter(list, getContext());
        rv_data.setAdapter(adapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                list.clear();
                list = DBHelper.getInstance().findAll(Order.class);
                adapter.refresh(list);

                refreshLayout.finishRefresh(500);
            }
        });



        adapter.setOnLongClickLinster((int position, View view) -> {
            new CustomDialog(getContext(), R.style.dialog_style)
                    .setTitle("是否要删除该订单")
                    .setContent("删完了就没了啊")
                    .setRightBtnText("确定")
                    .setLeftBtnText("取消")
                    .setRightBtnColor(Color.BLUE)
                    .setLeftListener(new OnLeftLinster() {
                        @Override
                        public void onClick(IDialogView view) {
                            L.e("点击左边");
                            CustomDialog dialog = (CustomDialog) view;
                            dialog.dismiss();
                        }
                    }).setRightListener(new OnRightLinster() {
                @Override
                public void onClick(IDialogView view) {
                    DBHelper.getInstance().deleteById(Order.class, list.get(position).getId());
                    showToast("删除成功");
                    list.remove(position);
                    adapter.refresh(list);
                    CustomDialog dialog = (CustomDialog) view;
                    dialog.dismiss();
                }
            }).show();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_zm_fragment2;
    }



    //下面是友盟
    @RegisterRxBus(101)
    private void refreshData(String share, int tag) {
        list.clear();
        list = DBHelper.getInstance().findAll(Order.class);
        adapter.refresh(list);
    }

    @Override
    public void onRightClick(View v) {
        showToast("点击筛选");
    }
}
