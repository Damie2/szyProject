package com.smh.szyproject.test.mvvm;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;


import androidx.databinding.DataBindingUtil;

import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.databinding.ActivityNewMvvmBinding;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.utils.PhoneInfo;
import com.smh.szyproject.utils.SPUtil;
import com.smh.szyproject.utils.utilCode.GsonUtils;

/**
 * 看这个啊啊啊啊啊啊啊  https://www.jianshu.com/p/4f28ef25c8f6
 * <p>
 * author : smh
 * date   : 2020/4/8 13:37
 * desc   :
 */
public class newMvvm extends BaseActivity implements WebViewContract.View {
    private WebViewPresenter presenter;
    ActivityNewMvvmBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_mvvm;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        presenter = new WebViewPresenter(this, this);
        initPermissions();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_mvvm);

    }
    @Permissions(Permission.READ_PHONE_STATE)
    private void initPermissions() {
        L.e("1.安装APP后发送请求");
        String imei = "";
        if (Build.VERSION.SDK_INT < 29) {
            imei = PhoneInfo.getIMEI(newMvvm.this);
        } else {
            imei = Settings.System.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        L.e("imei:" + imei);
        PhoneParameter parameter = new PhoneParameter();
        parameter.setImei(imei);
        parameter.setImsi("");
        parameter.setToken(SPUtil.getString("token", "", newMvvm.this));
        parameter.setUdid("1asdfasdfasdfadf");
        parameter.setOperator("");
        parameter.setIccid("");
        presenter.sendParameter(parameter);
        L.e("1.手机参数是:" + GsonUtils.getGson().toJson(parameter));

    }

    @Override
    public void getResult(Result result) {
        L.e("结果是:" + result.getInfo());
        result.setImgUrl("http://f.hiphotos.baidu.com/image/pic/item/dbb44aed2e738bd4f60e4017a48b87d6277ff9ed.jpg");
        binding.setResult(result);
    }
}
