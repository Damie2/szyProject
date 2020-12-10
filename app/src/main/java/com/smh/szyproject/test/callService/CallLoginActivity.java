package com.smh.szyproject.test.callService;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.mvp.bean.User;
import com.smh.szyproject.mvp.module.CallLoginContract;
import com.smh.szyproject.mvp.presenter.CallLoginPresenter;
import com.smh.szyproject.other.helper.InputTextHelper;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/9/22 14:04
 * desc   :
 */
public class CallLoginActivity extends BaseActivity implements View.OnClickListener, CallLoginContract.View {

    @BindView(R.id.et_login_phone)
    EditText etPhone;
    //    @BindView(R.id.et_login_password)
//    PasswordEditText etPas;
    @BindView(R.id.btn_login_commit)
    Button button;
    CallLoginPresenter presenter;
    String phone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_call;
    }

    @Permissions({Permission.MANAGE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE, Permission.RECORD_AUDIO,Permission.ANSWER_PHONE_CALLS})
    @Override
    public void init(Bundle savedInstanceState) {
        presenter = new CallLoginPresenter(this, this);
        InputTextHelper.with(this)
                .addView(etPhone)
                .setMain(button)
                .setListener(helper -> etPhone.getText().toString().length() > 0)
                .build();
        initWindow();

        if(SPUtil.getInt("id",0,this)!=0){
            close();
        }
    }

    private void initWindow() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        }
    }


    @OnClick(R.id.btn_login_commit)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_commit:
                login();
                break;
        }
    }

    private void login() {
        L.e("点击了");
        phone = etPhone.getText().toString();
        User user = new User();
        user.setPhone(phone);
        presenter.sendUser(user);
    }

    @Override
    public void close() {
        startActivity(CallMainActivity.class);
    }
}
