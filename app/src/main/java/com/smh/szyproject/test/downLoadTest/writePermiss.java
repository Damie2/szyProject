package com.smh.szyproject.test.downLoadTest;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

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
         fileName = System.currentTimeMillis() + ".exe";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            saveFilePath = MyApplication.getContext().getExternalFilesDir("file") + fileName;
//        } else {
//            saveFilePath = Environment.getExternalStorageDirectory() + "/download/" + fileName;
        saveFilePath = Environment.getExternalStorageDirectory() + "/" + fileName;
            //测试路径都能下载

//        https://blog.csdn.net/weixin_40611659/article/details/95174124

//        看我，看我，看我
//        PathUtils中 getExternalAppPicturesPath方法
//        getExternalAppDownloadPath
//                getExternalAppDcimPath
//        getExternalAppDocumentsPath
//        }

//        如果要访问沙盒外的媒体共享文件，比如照片，音乐，视频等，需要申请新的媒体权限:READ_MEDIA_IMAGES,READ_MEDIA_VIDEO,READ_MEDIA_AUDIO,申请方法同原来的存储权限
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
