package com.smh.szyproject.test.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.CommonAdapter;
import com.smh.szyproject.other.utils.AppUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * author : smh
 * date   : 2020/7/14 17:07
 * desc   :
 */
public class CustomPopwindow extends PopupWindow implements View.OnClickListener {
    private ImageView iv_close;
    private View view;
    private List<String> list;
    Context context;
    private RecyclerView recyclerView;

    private RelativeLayout rl_root;

    private ClassifyAdapter adapter;
    private SelectClassifyLinstener linstener;

    public CustomPopwindow(Context context, List<String> list, SelectClassifyLinstener linstener, Activity activity) {
        super(context);
        this.context = context;
        this.list = list;
        this.linstener = linstener;
        init(activity);
    }

    private void init(Activity activity) {
        view = LayoutInflater.from(context).inflate(R.layout.test_pw_classify, null);
        AutoUtils.auto(view);
        recyclerView = view.findViewById(R.id.rv_classify);
        rl_root = view.findViewById(R.id.root);
        view.setOnClickListener(this);
        iv_close = view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        adapter = new ClassifyAdapter(list, context);

        adapter.setOnClickLinster(new CommonAdapter.OnItemClickLinster() {
            @Override
            public void onItemClick(int position, View view) {
                int old = adapter.getSelectPosiion();
                adapter.setSelectPosiion(position);
                adapter.notifyItemChanged(position);
                adapter.notifyItemChanged(old);
                linstener.selected(list.get(position));
                dismiss();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.setAdapter(adapter);
        rl_root.setPadding(rl_root.getPaddingLeft(), AppUtils.getStateBarHeight(activity), rl_root.getPaddingRight(), rl_root.getPaddingBottom());

        setContentView(view);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.setTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setClippingEnabled(false);  //全屏
    }


    @Override
    public void onClick(View view) {
        //点击任意一个popwindow消失
        this.dismiss();
    }

    public static interface SelectClassifyLinstener {
        void selected(String msg);
    }

}
