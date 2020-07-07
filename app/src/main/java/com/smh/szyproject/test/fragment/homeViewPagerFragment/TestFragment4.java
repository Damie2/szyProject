package com.smh.szyproject.test.fragment.homeViewPagerFragment;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment4 extends BaseFragment {
    @Override
    protected void init() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test4;
    }


    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

}
