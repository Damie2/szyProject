package com.smh.szyproject.test.fragment.lunzihomeViewPager;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment2 extends BaseFragment {
    @Override
    protected void init() {
//        BarUtils.setStatusBarLightMode(getActivity(),true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test2;
    }
    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }


}
