package com.smh.szyproject.test.downLoadTest;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.hjq.http.model.FileContentResolver;
import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.FileUtil;
import com.smh.szyproject.other.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import butterknife.BindView;

/**
 * @Author smh
 * @Date 2022/2/24 13:47
 */
public class shaheActivity extends BaseActivity {
    @BindView(R.id.tv_next)
    TextView textView;
    private String path = "https://down.360safe.com/360ap/360freeap_beta_setup_freewifi.exe";
    String fileName;
    File file;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        textView.setOnClickListener(new View.OnClickListener() {

            // 不适配 Android 11 可以这样写
//            .permission(Permission.Group.STORAGE)
            // 适配 Android 11 需要这样写，这里无需再写 Permission.Group.STORAGE
//            外部存储权限（特殊权限，需要 Android 11 及以上）
            @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
            @Override
            public void onClick(View view) {
                L.e("开始写文件");
                fileName = System.currentTimeMillis() + ".exe";
                fenqu();
//                bufenqu();
                method1();
            }
        });
    }

    /**
     * 放到外部存储的应用专属目录则不需要适配分区存储特性
     */
    private void bufenqu() {
        ///storage/emulated/0/Android/data/com.smh.szyproject/files/Download/微信 8.0.15.apk
        file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "微信 8.0.15.apk");
    }

    /**
     * 适配分区存储
     *
     * https://www.yht7.com/news/13427
     */
    private void fenqu(){
        String fileName = "微信 8.0.15.apk";
            Uri outputUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // 适配 Android 10 分区存储特性
                ContentValues values = new ContentValues();
                // 设置显示的文件名
                values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
                // 生成一个新的 uri 路径
                outputUri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                file = new FileContentResolver(getContentResolver(), outputUri, fileName);
            } else {
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            }

    }



    private void method1() {
        L.e("开始下载");
        RequestParams params = new RequestParams(path);
        params.setSaveFilePath(file.getAbsolutePath());
        params.setAutoRename(false);
        params.setAutoResume(false);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                L.e("开始下载：" +file.getAbsolutePath());
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                L.e("进度:" + (int) (current * 100 / total));
            }

            @Override
            public void onSuccess(File result) {
                L.e("下载成功：" + result.getAbsolutePath());
                File file = new File(result.getAbsolutePath());
                if (FileUtil.isFileExist(result.getAbsolutePath())) {
                    L.e("长度是:" + file.length());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                L.e("下载失败" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                L.e("onCancelled" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                L.e("onFinished");
            }
        });
    }
}
