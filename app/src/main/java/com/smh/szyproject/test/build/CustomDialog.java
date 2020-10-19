package com.smh.szyproject.test.build;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
public class CustomDialog extends Dialog implements View.OnClickListener, IDialogView {

    private ViewGroup viewGroup;
    private OnLeftLinster leftListener;
    private OnRightLinster rightListener;
    @BindView(R.id.btn_left)
    Button btn_left;
    @BindView(R.id.btn_right)
    Button btn_right;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.line_center)
    View line;
    @BindView(R.id.rl_group)
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.auto(viewGroup);
        setContentView(viewGroup);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        ButterKnife.bind(this, viewGroup);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
//        setCanceledOnTouchOutside(false);//这个无效的原因是xml布局是全屏的，所以无效，可以给其他布局设置个点击事件，点击后就退出全屏
    }

    public CustomDialog setLeftListener(OnLeftLinster leftListener) {
        this.leftListener = leftListener;
        return this;
    }

    public CustomDialog setRightListener(OnRightLinster rightListener) {
        this.rightListener = rightListener;
        return this;
    }


    public CustomDialog setViewContent(View view) {
        ll_content.removeAllViews();
        ll_content.addView(view);
        return this;
    }

    public CustomDialog setLeftBtnColor(int color) {
        btn_left.setTextColor(color);
        return this;
    }

    public CustomDialog setRightBtnColor(int color) {
        btn_right.setTextColor(color);
        return this;
    }


    public CustomDialog setTitle(String text) {
        tv_title.setText(text);
        return this;
    }

    public CustomDialog setTitleColor(int color) {
        tv_title.setTextColor(color);
        return this;
    }

    public CustomDialog setLeftBtnText(String text) {
        btn_left.setText(text);
        return this;
    }

    public CustomDialog setRightBtnText(String text) {
        btn_right.setText(text);
        return this;
    }

    public CustomDialog setContent(String text) {
        tv_content.setText(text);
        return this;
    }

//    public CustomDialog setLineGone() {
//        line.setVisibility(View.GONE);
//        return this;
//    }

    public CustomDialog setLeftBtnGone() {
        btn_left.setVisibility(View.GONE);
        return this;
    }

    public CustomDialog setRightBtnLayoutCentent() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        btn_right.setTextSize(12);
        AutoUtils.auto(btn_right);
        btn_right.setLayoutParams(lp);
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
        if (leftListener != null)
            leftListener.onClick(this);
    }

    @Override
    public void onMiddleClick() {

    }

    @Override
    public void onRightClick() {
        if (rightListener != null)
            rightListener.onClick(this);
    }
}
