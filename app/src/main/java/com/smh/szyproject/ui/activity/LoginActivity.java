package com.smh.szyproject.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.helper.InputTextHelper;
import com.smh.szyproject.umeng.Platform;
import com.smh.szyproject.umeng.UmengClient;
import com.smh.szyproject.umeng.UmengLogin;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.widget.other.KeyboardWatcher;
import com.smh.szyproject.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/4/9 13:59
 * desc   :
 */
public class LoginActivity extends BaseActivity implements UmengLogin.OnLoginListener,
        KeyboardWatcher.SoftKeyboardStateListener {
    @BindView(R.id.iv_login_logo)
    ImageView mLogoView;

    @BindView(R.id.ll_login_body)
    LinearLayout mBodyLayout;
    @BindView(R.id.et_login_phone)
    EditText mPhoneView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;

    @BindView(R.id.btn_login_commit)
    Button mCommitView;

    @BindView(R.id.v_login_blank)
    View mBlankView;

    @BindView(R.id.ll_login_other)
    View mOtherView;
    @BindView(R.id.iv_login_qq)
    View mQQView;
    @BindView(R.id.iv_login_wx)
    View mWeChatView;

    /**
     * logo 缩放比例
     */
    private final float mLogoScale = 0.8f;
    /**
     * 动画时间
     */
    private final int mAnimTime = 300;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mPasswordView)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 &&
                        mPasswordView.getText().toString().length() >= 6)
                .build();

//        setOnClickListener(R.id.tv_login_forget, R.id.btn_login_commit, R.id.iv_login_qq, R.id.iv_login_wx);

        initData();


    }

    protected void initData() {

        mBodyLayout.postDelayed(() -> {
            // 因为在小屏幕手机上面，因为计算规则的因素会导致动画效果特别夸张，所以不在小屏幕手机上面展示这个动画效果
            if (mBlankView.getHeight() > mBodyLayout.getHeight()) {
                // 只有空白区域的高度大于登录框区域的高度才展示动画
                KeyboardWatcher.with(LoginActivity.this)
                        .setListener(LoginActivity.this);
            }
        }, 500);

        // 判断用户当前有没有安装 QQ
        if (!UmengClient.isAppInstalled(this, Platform.QQ)) {
            mQQView.setVisibility(View.GONE);
        }

        // 判断用户当前有没有安装微信
        if (!UmengClient.isAppInstalled(this, Platform.WECHAT)) {
            mWeChatView.setVisibility(View.GONE);
        }

        // 如果这两个都没有安装就隐藏提示
        if (mQQView.getVisibility() == View.GONE && mWeChatView.getVisibility() == View.GONE) {
            mOtherView.setVisibility(View.GONE);
        }

        // 填充传入的手机号和密码
//        mPhoneView.setText(getString(IntentKey.PHONE));
//        mPasswordView.setText(getString(IntentKey.PASSWORD));
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {

    }

    @Override
    public void onSoftKeyboardClosed() {

    }

    @Override
    public void onSucceed(Platform platform, UmengLogin.LoginData data) {
        L.e("SUCCESS");
        if (data != null) {
            L.e("登录成功，昵称是:" + data.getName());
        }
    }

    @OnClick(R.id.tv_login_forget)
    public void forget() {
        startActivity(PasswordForgetActivity.class);

//        startActivity(PasswordResetActivity.class);
    }



    @Override
    public void onRightClick(View v) {
        // 跳转到注册界面
        startActivity(RegisterActivity.class);
    }

    @OnClick(R.id.btn_login_commit)
    public void login_commit() {
        ToastUtils.showToastForText(this, "登录提交");
//        loginSuccess();


    }

    @OnClick(R.id.iv_login_qq)
    public void iv_login_qq() {
        ToastUtils.showToastForText(this, "QQ登录");
        Platform platform = Platform.QQ;
        UmengClient.login(this, platform, this);
    }

    @OnClick(R.id.iv_login_wx)
    public void iv_login_wx() {
        ToastUtils.showToastForText(this, "微信登录");
        Platform platform = Platform.WECHAT;
        UmengClient.login(this, platform, this);
    }

    public void loginSuccess(){
        String className =  getIntent().getStringExtra("className");
        if(!TextUtils.isEmpty(className)){
            ComponentName componentName = new ComponentName(this, className);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.putExtras(getIntent().getExtras());
            startActivity(intent);
        }
        finish();
    }
}
