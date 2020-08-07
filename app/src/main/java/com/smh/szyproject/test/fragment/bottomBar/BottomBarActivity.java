package com.smh.szyproject.test.fragment.bottomBar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/8/3 13:25
 * desc   :
 */
public class BottomBarActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    private FragmentManager mFragmentManager;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_bottombar;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);

        //单个item置背景色
        BottomBarTab tab = mBottomBar.getTabWithId(R.id.conversations);
        tab.setBackgroundColor(Color.WHITE);
        tab.setBadgeCount(123);
    }

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, FragmentFactory.getInstance().getFragment(tabId)).commit();
        }
    };
}
