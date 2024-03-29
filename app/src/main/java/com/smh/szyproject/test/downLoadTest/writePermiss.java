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
import com.smh.szyproject.MyApplication;
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
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/9/2 14:05
 * desc   :安卓11存储权限适配
 * <p>
 * https://www.jianshu.com/p/e94cea26e213
 *
 *
 * 下载到 /storage/emulated/0/1645681571700.exe
 *
 *
 */
public class writePermiss extends BaseActivity {
    @BindView(R.id.tv_next)
    TextView textView;
    private String path = "https://down.360safe.com/360ap/360freeap_beta_setup_freewifi.exe";
    private String saveFilePath;
    String fileName;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        textView.setOnClickListener(new View.OnClickListener() {
            // 不适配 Android 11 可以这样写
            //.permission(Permission.Group.STORAGE)
            // 适配 Android 11 需要这样写，这里无需再写 Permission.Group.STORAGE
            @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
            @Override
            public void onClick(View view) {
                writeFiles();
            }
        });
    }

    private void writeFiles() {
        L.e("开始写文件");
//         如果是放到外部存储目录下则需要适配分区存储
            String fileName = "EasyHttp.png";
            File file;
            Uri outputUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // 适配 Android 10 分区存储特性
                ContentValues values = new ContentValues();
                // 设置显示的文件名
                values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
                // 生成一个新的 uri 路径
                outputUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                file = new FileContentResolver(getContentResolver(), outputUri, fileName);
            } else {
                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName);
            }


        method1();
    }


    //xutils带进度条的
    private void method1() {
        L.e("开始下载");
        RequestParams params = new RequestParams(path);
        params.setSaveFilePath(saveFilePath);
        params.setAutoRename(false);
        params.setAutoResume(false);
//        x.http().get(params, new Callback.CommonCallback<File>() {
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

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
