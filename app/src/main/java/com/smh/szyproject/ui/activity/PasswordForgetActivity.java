package com.smh.szyproject.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.helper.InputTextHelper;
import com.smh.szyproject.utils.ToastUtils;
import com.smh.szyproject.widget.CountdownView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/4/9 14:43
 * desc   :
 */
public class PasswordForgetActivity extends BaseActivity {
    @BindView(R.id.et_password_forget_phone)
    EditText mPhoneView;
    @BindView(R.id.et_password_forget_code)
    EditText mCodeView;
    @BindView(R.id.cv_password_forget_countdown)
    CountdownView mCountdownView;
    @BindView(R.id.btn_password_forget_commit)
    Button mCommitView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_password_forget;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        InputTextHelper.with(this)
                .addView(mPhoneView)
                .addView(mCodeView)
                .setMain(mCommitView)
                .setListener(helper -> mPhoneView.getText().toString().length() == 11 && mCodeView.getText().toString().length() == 4)
                .build();
    }

    @OnClick(R.id.cv_password_forget_countdown)
    public void countdown() {
        if (mPhoneView.getText().toString().length() != 11) {
            ToastUtils.showToastForText(this, "手机号输入不正确");
            return;
        }

        if (true) {
            ToastUtils.showToastForText(this, "验证码已发送，请注意查收");
            mCountdownView.start();
            return;
        }

        mCountdownView.start();


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
//                });
    }

    @OnClick(R.id.btn_password_forget_commit)
    public void commit() {
//        if (true) {
            ToastUtils.showToastForText(this, "进入重置密码页面");
            startActivity(PasswordResetActivity.class);
            finish();
//            return;
//        }

        // 验证码校验
//        EasyHttp.post(this)
//                .api(new VerifyCodeApi()
//                        .setPhone(mPhoneView.getText().toString())
//                        .setCode(mCodeView.getText().toString()))
//                .request(new HttpCallback<HttpData<Void>>(this) {
//
//                    @Override
//                    public void onSucceed(HttpData<Void> data) {
//                        PasswordResetActivity.start(getActivity(), mPhoneView.getText().toString(), mCodeView.getText().toString());
//                        finish();
//                    }
//                });
    }

}
