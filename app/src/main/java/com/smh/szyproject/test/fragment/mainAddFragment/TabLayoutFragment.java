package com.smh.szyproject.test.fragment.mainAddFragment;

import android.content.Intent;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.test.fragment.repace.MyRepaceActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/4 10:23
 * desc   :
 */
public class TabLayoutFragment extends BaseFragment {
    @BindView(R.id.tv_center)
    TextView textView;

    @BindView(R.id.tv_mgzx)
    TextView help;

    @Override
    protected void init() {
        textView.setText(getArguments().getString("name"));

        help.setOnClickListener((view)->{
            startActivity(new Intent(getContext(), MyRepaceActivity.class));
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.test_fragment_tab_layout;
    }
}
