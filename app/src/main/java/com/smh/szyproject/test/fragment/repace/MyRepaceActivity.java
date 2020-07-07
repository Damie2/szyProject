package com.smh.szyproject.test.fragment.repace;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseFragment;
import com.smh.szyproject.common.base.ToolBarActivity;
import com.smh.szyproject.other.utils.L;

import butterknife.BindView;

/**
 * Create by smh on 2018/11/23.
 */
public class MyRepaceActivity extends ToolBarActivity implements View.OnClickListener {

    @BindView(R.id.tv_answered)
    TextView tvAnswered;
    @BindView(R.id.v_answered)
    View vAnswered;
    @BindView(R.id.rl_answered)
    RelativeLayout rlAnswered;

    @BindView(R.id.tv_to_be_answered)
    TextView tvToBeAnswered;
    @BindView(R.id.v_to_be_answered)
    View vToBeAnswered;
    @BindView(R.id.rl_to_be_answered)
    RelativeLayout rlToBeAnswered;

    @BindView(R.id.fl_content)
    FrameLayout flContentCi;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String className;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_answered) {
            vAnswered.setVisibility(View.VISIBLE);
            vToBeAnswered.setVisibility(View.GONE);
            changeFragment(MyQAAnsweredFragment.class);
        }
        if (v.getId() == R.id.rl_to_be_answered) {
            vAnswered.setVisibility(View.GONE);
            vToBeAnswered.setVisibility(View.VISIBLE);
            changeFragment(MyQAUnAnsweredFragment.class);
        }
    }

    @Override
    protected String getToolBarTitle() {
        return "我的问答";
    }

    @Override
    public void init(Bundle savedInstanceState) {
        rlAnswered.setOnClickListener(this);
        rlToBeAnswered.setOnClickListener(this);
        changeFragment(MyQAAnsweredFragment.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_qa_fragment;
    }



    public void changeFragment(Class<? extends BaseFragment> classFragment) {
        className = classFragment.getName();
        L.d("名字是:" + className);
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
