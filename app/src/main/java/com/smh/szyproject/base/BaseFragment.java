package com.smh.szyproject.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smh.szyproject.utils.ActionBarHelper;
import com.zhy.autolayout.utils.AutoUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by android on 2018/7/3.
 */

public abstract class BaseFragment extends Fragment {
    private View view;
    Unbinder unbinder;

    public View getRootView() {
        return view;
    }

    private boolean isFrist = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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



        return view;
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if ((getUserVisibleHint() && isFrist) || savedInstanceState != null) {
            isFrist = false;
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
    }
}
