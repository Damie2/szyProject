package com.smh.szyproject.test.fragment.bottomBar;


import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.test.fragment.lunzihomeViewPager.TestFragment1;
import com.smh.szyproject.test.fragment.lunzihomeViewPager.TestFragment2;
import com.smh.szyproject.test.fragment.lunzihomeViewPager.TestFragment3;

/**
 * 创建者:   Leon
 * 创建时间:  2016/10/17 22:05
 * 描述：    TODO
 */
public class FragmentFactory {
    public static final String TAG = "FragmentFactory";

    private static FragmentFactory sFragmentFactory;

    private BaseFragment mMessageFragment;
    private BaseFragment mContactFragment;
    private BaseFragment mDynamicFragment;

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    public BaseFragment getFragment(int id) {
        switch (id) {
            case R.id.conversations:
                return getConversationFragment();
            case R.id.contacts:
                return getContactFragment();
            case R.id.dynamic:
                return getDynamicFragment();
        }
        return null;
    }

    private BaseFragment getConversationFragment() {
        if (mMessageFragment == null) {
            mMessageFragment = new TestFragment1();
        }
        return mMessageFragment;
    }

    private BaseFragment getDynamicFragment() {
        if (mDynamicFragment == null) {
            mDynamicFragment = new TestFragment2();
        }
        return mDynamicFragment;
    }

    private BaseFragment getContactFragment() {
        if (mContactFragment == null) {
            mContactFragment = new TestFragment3();
        }
        return mContactFragment;
    }




}
