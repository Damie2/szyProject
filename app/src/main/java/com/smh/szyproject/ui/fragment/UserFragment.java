package com.smh.szyproject.ui.fragment;


import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.smh.szyproject.R;
import com.smh.szyproject.Rx.databus.RegisterRxBus;
import com.smh.szyproject.Rx.databus.RxBus;
import com.smh.szyproject.base.BaseFragment;
import com.smh.szyproject.test.tablayoutsamples.ui.SimpleCardFragment;
import com.smh.szyproject.test.tablayoutsamples.ui.SlidingTabActivity;
import com.smh.szyproject.test.tablayoutsamples.utils.ViewFindUtils;
import com.smh.szyproject.utils.L;

import java.util.ArrayList;

import butterknife.BindView;


public class UserFragment extends BaseFragment {
    @BindView(R.id.tl_1)
    SlidingTabLayout layout;


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };

    private MyPagerAdapter mAdapter;
    @Override
    protected void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        RxBus.getInstance().register(this);
        initView();
    }

    private void initView() {
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }

        View decorView = getActivity().getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getFragmentManager());
        vp.setAdapter(mAdapter);
        SlidingTabLayout tabLayout_1 = ViewFindUtils.find(decorView, R.id.tl_1);
        tabLayout_1.setViewPager(vp);
        tabLayout_1.showDot(0);//显示红点
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
    public void getMsg(String push, int tag) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
