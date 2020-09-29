package com.smh.szyproject.common.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.smh.szyproject.R;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.action.TitleBarAction;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.ToastUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by android on 2018/7/3.
 */

public abstract class BaseFragment extends Fragment implements TitleBarAction {
    private View view;
    Unbinder unbinder;
    /** 状态栏沉浸 */
    private ImmersionBar mImmersionBar;
    public View getRootView() {
        return view;
    }

    private boolean isFrist = true;
    /**
     * 标题栏对象
     */
    private TitleBar mTitleBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    @Nullable
    public TitleBar getTitleBar() {
        if (mTitleBar == null) {
            mTitleBar = findTitleBar((ViewGroup) getRootView());
        }
        return mTitleBar;
    }

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }
    public void showToast(String msg){
        ToastUtils.showToastForText(getContext(),msg);
    }

    public boolean isNotEmpty(String text) {
        if (TextUtils.isEmpty(text))
            return false;
        else
            return true;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        AutoUtils.auto(view);
        ButterKnife.bind(this, view);
        //灰色状态栏设置为透明色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getActivity().getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {
            }
        }

        if (getTitleBar() != null) {
            getTitleBar().setOnTitleBarListener(this);
        }
        RxBus.getInstance().register(this);
        //字体默认白底黑字
//        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //初始化沉浸式
        return view;
    }



    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        // 初始化沉浸式状态栏
        createStatusBarConfig().init();
        // 设置标题栏沉浸
        if (mTitleBar != null) {
            ImmersionBar.setTitleBar(this, mTitleBar);
        }
    }
    /**
     * 初始化沉浸式状态栏
     */
    protected ImmersionBar createStatusBarConfig() {
        // 在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(isStatusBarDarkFont());;
        return mImmersionBar;
    }
    /**
     * 状态栏字体深色模式
     */
    protected boolean isStatusBarDarkFont() {
        return true;
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    @Nullable
    public ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }


    @Override
    public void onResume() {
        super.onResume();
        // 重新初始化状态栏
//        UmengClient.onResume(this);
    }

    @Override
    public void onPause() {
//        UmengClient.onPause(this);
        super.onPause();
    }
    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ((getUserVisibleHint() && isFrist) || savedInstanceState != null) {
            isFrist = false;
            initImmersionBar();
            init();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // 通过这两个判断，就可以知道什么时候去加载数据了
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isVisible() && isFrist) {
            isFrist = false;
            init(); // 加载数据的方法
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        RxBus.getInstance().unRegister(this);
    }
}
