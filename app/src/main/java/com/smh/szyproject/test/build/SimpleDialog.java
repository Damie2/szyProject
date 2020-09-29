package com.smh.szyproject.test.build;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.smh.szyproject.R;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * author : smh
 * date   : 2020/7/16 10:42
 * desc   :
 */
public class SimpleDialog extends Dialog implements View.OnClickListener, IDialogView {

    private ViewGroup viewGroup;
    private OnLeftLinster leftLinster;
    private OnRightLinster rightLinster;
    @BindView(R.id.btn_left)
    Button btn_left;
    @BindView(R.id.btn_right)
    Button btn_right;

    @BindView(R.id.tv_content)
    TextView tv_content;
    View line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.auto(viewGroup);
        setContentView(viewGroup);
    }

    public SimpleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.simple_dialog, null);
        ButterKnife.bind(this, viewGroup);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

    }

    public SimpleDialog setLeftLinster(OnLeftLinster leftLinster) {
        this.leftLinster = leftLinster;
        return this;
    }

    public SimpleDialog setRightLinster(OnRightLinster rightLinster) {
        this.rightLinster = rightLinster;
        return this;
    }

    public SimpleDialog setLeftBtnColor(int color) {
        btn_left.setTextColor(color);
        return this;
    }

    public SimpleDialog setRightBtnColor(int color) {
        btn_right.setTextColor(color);
        return this;
    }


    public SimpleDialog setLeftBtnText(String text) {
        btn_left.setText(text);
        return this;
    }

    public SimpleDialog setRightBtnText(String text) {
        btn_right.setText(text);
        return this;
    }

    public SimpleDialog setContent(String text) {
        tv_content.setText(text);
        return this;
    }


    public SimpleDialog setLeftBtnGone() {
        btn_left.setVisibility(View.GONE);
        return this;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                onLeftClick();
                break;
            case R.id.btn_right:
                onRightClick();
                break;
            default:
                break;
        }
    }


    @Override
    public void onLeftClick() {
        if (leftLinster != null)
            leftLinster.onClick(this);
    }

    @Override
    public void onMiddleClick() {

    }

    @Override
    public void onRightClick() {
        if (rightLinster != null)
            rightLinster.onClick(this);
    }
}
