package com.smh.szyproject.test.popwindow;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.common.base.CommonPopupWindow;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.Utils;
import com.smh.szyproject.test.rxjava.RxJavaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/7/14 16:43
 * desc   :
 */
public class PopWindowActivityTwo extends BaseActivity implements View.OnClickListener, CustomPopwindow.SelectClassifyLinstener {

    CustomPopwindow popwindow;

    @BindView(R.id.rl)
    RelativeLayout rl_root;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setPopupWindow();
    }

    private void setPopupWindow() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }
        popwindow = new CustomPopwindow(this, list, this, this);


    }


    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
//                popwindow.showAtLocation(rl_root, Gravity.TOP, 0, -Utils.getNavigationBarSize(this).y);

//                popwindow.showAtLocation(rl_root, Gravity.BOTTOM, 0, -Utils.getNavigationBarSize(this).y);

                startActivity(RxJavaActivity.class);

                break;
            default:
                break;
        }
    }

    @Override
    public void selected(String msg) {
        L.e("内容是:" + msg);
    }


    @Override
    protected void onPause() {
        L.e("onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        L.e("onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        L.e("onStop");
        super.onStop();
    }
}
