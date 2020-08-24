package com.smh.szyproject.test.login;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.aop.Login;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SPUtil;
import com.smh.szyproject.ui.activity.AboutActivity;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/8/12 11:33
 * desc   :
 */
public class testLoginActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        SPUtil.putString("token","123",this);
    }

//    @Login
//    @OnClick(R.id.tv_next)
//    @Override
//    public void onClick(View view) {
//        if(view.getId()==R.id.tv_next){
//            L.e("如果token为空，点击这个就直接登录，但是登录完之后还是这一页");
//        }
//    }

    @Login(className = "com.smh.szyproject.ui.activity.AboutActivity")
    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_next){
            L.e("如果未登录情况下，这样写，在登录完成之后，会执行跳转到AboutActivity这个页面");
            L.e("如果已登录，就输出这两句话");
            L.e("没登录就不输出拉");
        }
    }
//
//    @Login
//    @OnClick(R.id.tv_next)
//    @Override
//    public void onClick(View view) {
//        if(view.getId()==R.id.tv_next){
//
//        }
//    }

}
