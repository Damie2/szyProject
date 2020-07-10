package com.smh.szyproject.test.fragment.lunzihomeViewPager;

import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment3 extends BaseFragment {
    @Override
    protected void init() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
//        ImmersionBar.setTitleBar(getActivity(), textView);//这个就相当于paddingbar的内边距
        //设置状态栏字体颜色深色还是浅色,true是浅色
        setRightTitle("客服");
        setLeftTitle("返回");


    }

    @Override
    public void onLeftClick(View v) {
        getActivity().finish();
    }

    @Override
    public void onRightClick(View v) {
        showToast("我是客服");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test3;
    }


}
