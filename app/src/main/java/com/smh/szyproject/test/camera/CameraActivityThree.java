package com.smh.szyproject.test.camera;

import android.os.Bundle;
import android.view.View;
import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/9/10 13:22
 * desc   :SurfaceView使用
 */
public class CameraActivityThree extends BaseActivity implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Permissions(Permission.CAMERA)
    @Override
    public void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_start)
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_start){

        }
    }

}
