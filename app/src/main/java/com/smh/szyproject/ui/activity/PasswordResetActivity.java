package com.smh.szyproject.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.helper.InputTextHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/4/9 15:19
 * desc   :
 */
public class PasswordResetActivity extends BaseActivity {
    @BindView(R.id.et_password_reset_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_password_reset_password2)
    EditText mPasswordView2;
    @BindView(R.id.btn_password_reset_commit)
    Button mCommitView;

    /** 手机号 */
    private String mPhone;
    /** 验证码 */
    private String mCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_password_reset;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        InputTextHelper.with(this)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(helper -> mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()))
                .build();
    }

    @OnClick(R.id.btn_password_reset_commit)
    public void commit(){
        showToast("重置成功");
        finish();
    }


}
