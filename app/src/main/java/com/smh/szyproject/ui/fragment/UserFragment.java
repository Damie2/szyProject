package com.smh.szyproject.ui.fragment;


import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.Rx.databus.RegisterRxBus;
import com.smh.szyproject.Rx.databus.RxBus;
import com.smh.szyproject.base.BaseFragment;
import com.smh.szyproject.utils.L;

import butterknife.BindView;


public class UserFragment extends BaseFragment {
    @BindView(R.id.tv_center)
    TextView tvCenter;

    @Override
    protected void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        RxBus.getInstance().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_fragment;
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 透明状态栏
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }
    }
    @RegisterRxBus(1)
    public void getMsg(String push, int tag){
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }
}
