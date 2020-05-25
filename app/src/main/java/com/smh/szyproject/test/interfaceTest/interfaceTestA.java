package com.smh.szyproject.test.interfaceTest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.smh.szyproject.R;
import com.smh.szyproject.action.StatusAction;
import com.smh.szyproject.aop.Login;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.helper.PopupWindowHelper;
import com.smh.szyproject.widget.other.HintLayout;
import com.smh.szyproject.utils.SPUtil;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/3/9 14:56
 * desc   :
 */
public class interfaceTestA extends BaseActivity implements StatusAction {

    @BindView(R.id.layout)
    HintLayout layout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        SPUtil.putString("token","123",this);
        SPUtil.clear(this);
        InterfaceA inter = new AA();
        inter.test();


        ViewGroup group = getContentView();
        if(group!=null){

        }

//        getStatusBarConfig().statusBarDarkFont(true).init();

//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public HintLayout getHintLayout() {
        return layout;
    }

    public void tv_next(View view) {
        PopupWindowHelper helper = new PopupWindowHelper(this,R.layout.popwin_demo);
        helper.show(view);
    }
}
