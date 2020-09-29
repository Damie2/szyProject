package com.smh.szyproject.common.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.TitleBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.action.TitleBarAction;
import com.smh.szyproject.other.utils.ActionBarHelper;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.ToastUtils;
import com.umeng.message.PushAgent;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
//import com.zhy.autolayout.AutoFrameLayout;
//import com.zhy.autolayout.AutoLinearLayout;
//import com.zhy.autolayout.AutoRelativeLayout;

import org.w3c.dom.Text;

import butterknife.ButterKnife;

/**
 * Created by zm on 2018/10/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements TitleBarAction {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    /**
     * 标题栏对象
     */
    private TitleBar mTitleBar;

    /**
     * 状态栏沉浸
     */
    private ImmersionBar mImmersionBar;

    public KProgressHUD kProgressHUD;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarHelper.setHalfTransparent(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        RxBus.getInstance().register(this);

        PushAgent.getInstance(this).onAppStart();//友盟推送
        // 电量状态栏的颜色
//        StatusBarCompat.setStatusBarColor(this, getStatusBarColor(), true);

        //字体默认白底黑字
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (getTitleBar() != null) {
            getTitleBar().setOnTitleBarListener(this);
        }
        kProgressHUD = KProgressHUD.create(this);
        initImmersionBar();
        init(savedInstanceState);
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        // 初始化沉浸式状态栏
        createStatusBarConfig().init();
        // 设置标题栏沉浸
        if (mTitleBar != null) {
            ImmersionBar.setTitleBar(this, mTitleBar);
        }
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected ImmersionBar createStatusBarConfig() {
        // 在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                // 默认状态栏字体颜色为黑色
                .statusBarDarkFont(isStatusBarDarkFont());
//        https://github.com/gyf-dev/ImmersionBar
        return mImmersionBar;
    }

    /**
     * 状态栏字体深色模式
     */
    protected boolean isStatusBarDarkFont() {
        return true;
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    @Nullable
    public ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }


    //调登录，如果没有登录就走登录，登录了就直接走要走的那个页面
//    public void startActivityAfterLogin(Intent intent) {
//        String token = SPUtil.getString("token", "", this);
//        if (TextUtils.isEmpty(token)) {
//            ComponentName componentName = new ComponentName(this, LoginActivity.class);
//            intent.putExtra("className", intent.getComponent().getClassName());
//            intent.setComponent(componentName);
//            super.startActivity(intent);
//        } else {
//            super.startActivity(intent);
//        }
//    }

    //    showCustomHUD(R.drawable.anim_png_xml,"我是自定义异常");
    public void showCustomHUD(int resid, String message) {
        if (!kProgressHUD.isShowing()) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(resid);
            AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
            drawable.start();

            kProgressHUD.setCustomView(imageView)
                    .setLabel(message)
                    .show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(1000);//毫秒
                        kProgressHUD.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


    public void showToast(String msg) {
        ToastUtils.showToastForText(this, msg);
    }


    public boolean isNotEmpty(String text) {
        if (TextUtils.isEmpty(text))
            return false;
        else
            return true;
    }

    public void showProgressHUD() {
        if (!kProgressHUD.isShowing()) {
            kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).show();
        }
    }

    public void dismissProgressHUD() {
        kProgressHUD.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 重新初始化状态栏
//        UmengClient.onResume(this);
    }

    protected void onPause() {
//        UmengClient.onPause(this);
        super.onPause();
    }


    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(@StringRes int id) {
        setTitle(getString(id));
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (mTitleBar != null) {
            mTitleBar.setTitle(title);
        }
    }


    @Override
    @Nullable
    public TitleBar getTitleBar() {
        if (mTitleBar == null) {
            mTitleBar = findTitleBar(getContentView());
        }
        return mTitleBar;
    }


    @Override
    public void onLeftClick(View v) {
        onBackPressed();
    }


    @SuppressLint("ResourceType")
    protected int getStatusBarColor() {
//        return getResources().getColor(R.color.red);
        return 0;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }
        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public abstract int getLayoutId();

    public abstract void init(Bundle savedInstanceState);


    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivityFinish(new Intent(this, cls));
    }

    public void startActivityFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    /**
     * 和 setContentView 对应的方法
     */
    public ViewGroup getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }


    @Override
    public void startActivityForResult(Intent oldIntent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(oldIntent, requestCode, options);

    }
}
