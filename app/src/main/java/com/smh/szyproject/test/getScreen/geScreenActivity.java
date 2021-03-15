package com.smh.szyproject.test.getScreen;

import android.graphics.Rect;
import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.Utils;
import com.smh.szyproject.other.utils.utilCode.ScreenUtils;

public class geScreenActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //获取屏幕密度和分辨率
        Resolution();
        getHeight();

        ScreenUtils.getScreenHeight();//获得屏幕高度
        ScreenUtils.getScreenWidth();//获得屏幕宽度
        L.e("获得屏幕高度"+ScreenUtils.getScreenHeight());
        L.e("获得屏幕宽度"+ScreenUtils.getScreenWidth());
        L.e("获得应用的高度"+ScreenUtils.getAppScreenHeight());
        L.e("获得应用的宽度"+ScreenUtils.getAppScreenWidth());
        L.e("获得屏幕密度"+ScreenUtils.getScreenDensity());
        L.e("dpi是:"+ScreenUtils.getScreenDensityDpi());
        //dip转PX
        L.e("屏幕的px:"+Utils.dip2px(this,ScreenUtils.getScreenDensityDpi()));



//        获取控件的高度或者宽度  true则为测量该控件的高度，heightOrWidth=false则为测量该控件的宽度
        int i = ScreenUtils.getViewHeightOrWidth(findViewById(R.id.tv_test),false);
        L.e("宽度是:"+i);
    }

    private void getHeight() {
//        getSupportSoftInputHeight  搜这个方法，获取软键盘的高度
        Rect r = new Rect();
        /*  *
         * decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
         * 通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。*/
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //获取屏幕的高度
        int screenHeight = this.getWindow().getDecorView().getRootView().getHeight();
        L.e("屏幕的高度是:"+screenHeight);
        L.e("未被遮挡的显示区域的底部高度:"+r.bottom);
        //计算软件盘的高度
        int softInputHeight = screenHeight - r.bottom;
        L.e("软键盘的高度是:"+softInputHeight);
    }

    private void Resolution() {
        float res = Utils.printResolution(this);
        L.e("屏幕密度是:" + res);
    }
}
