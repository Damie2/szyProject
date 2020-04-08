package com.smh.szyproject;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.smh.szyproject.Rx.databus.RxBus;
import com.smh.szyproject.common.image.ImageLoader;
import com.smh.szyproject.helper.ActivityStackManager;
import com.smh.szyproject.helper.DoubleClickHelper;
import com.smh.szyproject.helper.PopupWindowHelper;
import com.smh.szyproject.other.HintLayout;
import com.smh.szyproject.test.DesignPattern.Factory.FactoryTonA;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.ui.fragment.MainFragment;
import com.smh.szyproject.ui.fragment.UserFragment;
import com.smh.szyproject.ui.view.ViewHelp;
import com.smh.szyproject.utils.FragmentHelp;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.utils.ToastUtils;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
    @BindView(R.id.layout)
    HintLayout layout;

    private View currentSelectView;
    private int defaultColor = Color.parseColor("#a6a6a6");
    private int[] defaultIds = {R.drawable.article_1, R.drawable.wallet_1};
    private int[] selectIds = {R.drawable.article_2, R.drawable.wallet_2};
    private FragmentHelp fh;
    private Fragment[] fragments = new Fragment[this.defaultIds.length];
    private int oldId;
    private int selectColor = Color.parseColor("#a6a6a6");

    @BindView(R.id.tv_menu_first)
    TextView tv_menu_first;
    @BindView(R.id.tv_menu_second)
    TextView tv_menu_second;
    private int viewId = 0;


    private void initView(Bundle savedInstanceState) {

        String deviceId = "";

        if (Build.VERSION.SDK_INT < 29) {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        }else{
            deviceId = Settings.System.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }

        L.e("deviceId:"+deviceId);



        fh = new FragmentHelp(getSupportFragmentManager());
        if (savedInstanceState == null) {
            fragments[0] = new MainFragment();
            fragments[1] = new UserFragment();

        } else {
            fragments[0] = getSupportFragmentManager().findFragmentByTag(Constants.Tag.ARTICLE) == null ? new MainFragment() : getSupportFragmentManager().findFragmentByTag(Constants.Tag.ARTICLE);
            fragments[1] = getSupportFragmentManager().findFragmentByTag(Constants.Tag.VIDEO) == null ? new UserFragment() : getSupportFragmentManager().findFragmentByTag(Constants.Tag.VIDEO);

        }
        currentSelectView = tv_menu_first;
        if (viewId == 0) {
            switchViewById(R.id.tv_menu_first);
            fh.setCurrentFragment(Constants.Tag.ARTICLE, fragments[0]);
        } else {
            switchViewById(viewId);
            fh.setCurrentFragment(switchViewById(viewId), fragments[0]);
        }
    }

    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void init(Bundle paramBundle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        RxBus.getInstance().register(this);
        if (paramBundle != null) {
            viewId = paramBundle.getInt("viewId");
        }
        initView(paramBundle);
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("viewId", this.viewId);
    }

    public String switchViewById(int viewId) {
        String tag = null;
        int id = 0;
        TextView textView = null;
        switch (viewId) {
            case R.id.tv_menu_first:
                tag = Constants.Tag.ARTICLE;
                id = 0;
                textView = tv_menu_first;
                break;
            case R.id.tv_menu_second:
                tag = Constants.Tag.VIDEO;
                id = 1;
                textView = tv_menu_second;
                break;

        }
        fh.add(fragments[id], R.id.fl_content, tag);
        ViewHelp.getInstance().setImgForViewTop(currentSelectView, getResources().getDrawable(defaultIds[oldId])); //清除上一个view图片
        ((TextView) currentSelectView).setTextColor(defaultColor);
        textView.setTextColor(selectColor);
        ViewHelp.getInstance().setImgForViewTop(textView, getResources().getDrawable(selectIds[id]));//选中图片

        oldId = id;
        currentSelectView = textView;
        return tag;
    }


    public void switchViewById(View view) {
        switchViewById(view.getId());
    }

    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            layout.postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300);
        } else {
            ToastUtils.showToastForText(this,"再按一次退出");
        }
    }


}
