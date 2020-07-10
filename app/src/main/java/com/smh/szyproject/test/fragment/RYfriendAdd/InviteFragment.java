package com.smh.szyproject.test.fragment.RYfriendAdd;


import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;

public class InviteFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public void onClick(View view) {

    }

    @Override
    protected void init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test2;
    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                // 透明状态栏
//                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
//    }


}
