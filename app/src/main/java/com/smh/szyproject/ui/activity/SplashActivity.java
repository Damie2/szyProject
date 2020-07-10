package com.smh.szyproject.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/4/2 10:39
 * desc   :
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_splash_lottie)
    LottieAnimationView mLottieView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        ImmersionBar.with(this).fullScreen(true)
                // 隐藏状态栏
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                // 透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentNavigationBar().init();


        // 设置动画监听
        mLottieView.addAnimatorListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
//                startActivity(HomeActivity.class);

                initPermission();

            }
        });
    }

    @Permissions(Permission.WRITE_EXTERNAL_STORAGE)
    private void initPermission() {
//        startActivity(ZMactivity.class);
//        finish();
    }

//    //隐藏状态栏
//    @Override
//    public ImmersionBar createStatusBarConfig() {
//        return super.createStatusBarConfig()
//                // 有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .fullScreen(true)
//                // 隐藏状态栏
//                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
//                // 透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentNavigationBar();
//    }
}
