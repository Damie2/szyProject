package com.smh.szyproject.ui.fragment;

import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.Rx.databus.RxBus;
import com.smh.szyproject.base.BaseFragment;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/4 10:23
 * desc   :
 */
public class TabLayoutFragment extends BaseFragment {
    @BindView(R.id.tv_center)
    TextView textView;

    @Override
    protected void init() {
        textView.setText(getArguments().getString("name"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_layout;
    }
}
