package com.smh.szyproject.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.helper.InputTextHelper;
import com.smh.szyproject.other.widget.CountdownView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/4/9 15:56
 * desc   :
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.et_register_phone)
    EditText mPhoneView;
    @BindView(R.id.cv_register_countdown)
    CountdownView mCountdownView;

    @BindView(R.id.et_register_code)
    EditText mCodeView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    Button mCommitView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);

        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 &&
                        mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()))
                .build();

    }
    @Override
    public ImmersionBar createStatusBarConfig() {
        // 不要把整个布局顶上去
        return super.createStatusBarConfig().keyboardEnable(true);
    }

    @OnClick(R.id.cv_register_countdown)
    public void registerCountdown(){
        // 获取验证码
        if (mPhoneView.getText().toString().length() != 11) {
            showToast("手机号输入不正确");
            return;
        }

        if (true) {
            showToast("验证码已发送，请注意查收");
            mCountdownView.start();
            return;
        }

//        // 获取验证码
//        EasyHttp.post(this)
//                .api(new GetCodeApi()
//                        .setPhone(mPhoneView.getText().toString()))
//                .request(new HttpCallback<HttpData<Void>>(this) {
//
//                    @Override
//                    public void onSucceed(HttpData<Void> data) {
//                        toast(R.string.common_code_send_hint);
//                        mCountdownView.start();
//                    }
//
//                    @Override
//                    public void onFail(Exception e) {
//                        super.onFail(e);
//                        mCountdownView.start();
//                    }
//                });
    }

    @OnClick(R.id.btn_register_commit)
    public void commit(){
        if (true) {
//            LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
//            通过RXBUS接收用户名和密码
            setResult(RESULT_OK);
            finish();
            return;
        }
        // 提交注册
//        EasyHttp.post(this)
//                .api(new RegisterApi()
//                        .setPhone(mPhoneView.getText().toString())
//                        .setCode(mCodeView.getText().toString())
//                        .setPassword(mPasswordView1.getText().toString()))
//                .request(new HttpCallback<HttpData<RegisterBean>>(this) {
//
//                    @Override
//                    public void onSucceed(HttpData<RegisterBean> data) {
//                        LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
//                        setResult(RESULT_OK);
//                        finish();
//                    }
//                });
    }

}
