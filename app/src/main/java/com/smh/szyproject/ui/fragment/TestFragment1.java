package com.smh.szyproject.ui.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.test.tablayoutsamples.ui.SimpleCardFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment1 extends BaseFragment implements OnTabSelectListener {

    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private ArrayList<SimpleCardFragment> mFragments = new ArrayList<>();

    private MyPagerAdapter mAdapter;

    @BindView(R.id.tl_2)
    public SlidingTabLayout mTablayout;
    @BindView(R.id.vp)
    public ViewPager vp;

    @Override
    protected void init() {
        ImmersionBar.setTitleBar(this, mTablayout);
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        mTablayout.setViewPager(vp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test1;
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

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
