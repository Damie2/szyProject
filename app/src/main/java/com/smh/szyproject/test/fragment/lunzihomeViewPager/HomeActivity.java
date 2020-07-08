package com.smh.szyproject.test.fragment.lunzihomeViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.common.base.BaseFragmentAdapter;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.other.helper.DoubleClickHelper;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/4/2 11:21
 * desc   :
 */
public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //禁止左右滑动在NoScrollViewPager 的onTouchEvent 方法里
    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<BaseFragment> mPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        initData();
    }

    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(new TestFragment1());
        mPagerAdapter.addFragment(new TestFragment2());
        mPagerAdapter.addFragment(new TestFragment3());
        mPagerAdapter.addFragment(new TestFragment4());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            mViewPager.postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300);
        } else {
            showToast("再按一次退出");
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mPagerAdapter.setCurrentItem(TestFragment1.class);
                return true;
            case R.id.home_found:
                mPagerAdapter.setCurrentItem(TestFragment2.class);
                return true;
            case R.id.home_message:
                mPagerAdapter.setCurrentItem(TestFragment3.class);
                return true;
            case R.id.home_me:
                mPagerAdapter.setCurrentItem(TestFragment4.class);
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }
}
