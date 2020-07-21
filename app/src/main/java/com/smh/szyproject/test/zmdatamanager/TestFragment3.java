package com.smh.szyproject.test.zmdatamanager;

import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.other.utils.L;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author : smh
 * date   : 2020/4/2 11:43
 * desc   :
 */
public class TestFragment3 extends BaseFragment {
    @Override
    protected void init() {

        if (isMobilePhoneLegal("19996351235")) {
            L.e("是手机号");
        } else {
            L.e("不是手机号");
        }

        if (isMobilePhoneLegal("1996351235")) {
            L.e("1是手机号");
        } else {
            L.e("1不是手机号");
        }
    }


    public static boolean isMobilePhoneLegal(String mobile) {
        if (mobile.length() == 11 && mobile.startsWith("1")) {
            return true;
        }
        return false;
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
        return R.layout.test_zm_fragment3;
    }


}
