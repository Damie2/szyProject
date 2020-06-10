package com.smh.szyproject.test.SurfaceView;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;

import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/9 13:23
 * desc   :
 */
public class SurfaceViewActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.main_sf_camera)
    SurfaceView surfaceView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_surfaceview_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Permissions(Permission.CAMERA)
    @Override
    public void onClick(View v) {

    }
}
