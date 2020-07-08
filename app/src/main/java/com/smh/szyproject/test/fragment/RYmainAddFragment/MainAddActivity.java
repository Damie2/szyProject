package com.smh.szyproject.test.fragment.RYmainAddFragment;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.smh.szyproject.Constants;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.mvp.bean.VersionResult;
import com.smh.szyproject.other.Rx.databus.RxBus;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.other.helper.DoubleClickHelper;
import com.smh.szyproject.other.utils.FragmentHelp;
import com.smh.szyproject.other.widget.other.HintLayout;
import com.smh.szyproject.ui.view.ViewHelp;

import java.io.File;

import butterknife.BindView;


public class MainAddActivity extends BaseActivity implements OnButtonClickListener , OnDownloadListener {
    @BindView(R.id.layout)
    HintLayout layout;
    private DownloadManager manager;
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
            showToast("再按一次退出");
        }
    }
    private void startUpdate3(VersionResult result) {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置对话框强制更新时进度条和文字的颜色
                //.setDialogProgressBarColor(Color.parseColor("#E743DA"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置强制更新
                .setForcedUpgrade(true)
                //设置对话框按钮的点击监听
                .setButtonClickListener(this)
                //设置下载过程的监听
                .setOnDownloadListener(this);

        manager = DownloadManager.getInstance(this);
        manager.setApkName("ESFileExplorer.apk")
                .setApkUrl(result.getData().getPath())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(true)
                .setConfiguration(configuration)
                .setApkVersionCode(2)
                .setApkVersionName("2.1.8")
                .setApkSize("20.4")
                .setApkDescription("我是描述")
//                .setApkMD5("DC501F04BBAA458C9DC33008EFED5E7F")
                .download();
    }

    @Override
    public void onButtonClick(int id) {

    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void error(Exception e) {

    }
}
