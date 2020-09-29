package com.smh.szyproject.test.jetpack.liveData;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.fragment.repace.MyQAAnsweredFragment;

/**
 * author : smh
 * date   : 2020/9/21 18:10
 * desc   :
 */
public class LiveActivity extends BaseActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_kotlinmvvm;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        changeFragment(LiveDataFragment.class);
    }

    public void changeFragment(Class<? extends BaseFragment> classFragment) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        try {
            transaction.replace(R.id.fl_content, classFragment.newInstance());
            transaction.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
