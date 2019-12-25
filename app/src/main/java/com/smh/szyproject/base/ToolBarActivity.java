package com.smh.szyproject.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.utils.ToolBarHelper;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by android on 2018/6/4.
 */

public abstract class ToolBarActivity extends BaseActivity {
    private ToolBarHelper mToolBarHelper;
    public TextView tv_center;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        mToolBarHelper = new ToolBarHelper(this, layoutResID);
        tv_center = mToolBarHelper.getTvCenter();
        tv_center.setText(getToolBarTitle());
        View leftView = getLeftToolBarView();
        View rightView = getRightToolBarView();

        if (getLeftToolBarView() != null) {

            mToolBarHelper.getLeft().addView(leftView);
        }
        if (getRightToolBarView() != null) {

            mToolBarHelper.getRight().addView(rightView);
        }
        //返回帧布局视图
        AutoUtils.auto(mToolBarHelper.getContentView());
        setContentView(mToolBarHelper.getContentView());
    }

    public View getRightToolBarView() {
        return null;
    }

    public View getLeftToolBarView() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.ic_back);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mToolBarHelper.getLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return iv;
    }

    public void setTitle(String content) {
        tv_center.setText(content);
    }

    protected abstract String getToolBarTitle();


    public void back(View view) {
        finish();
    }

    public void hideToolBar() {
        mToolBarHelper.hideToolBar();
    }

    public void showToolBar() {
        mToolBarHelper.showToolBar();
    }
}
