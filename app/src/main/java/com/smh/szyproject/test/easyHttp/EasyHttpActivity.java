package com.smh.szyproject.test.easyHttp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.EasyUtils;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnDownloadListener;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.listener.OnUpdateListener;
import com.hjq.http.model.DataClass;
import com.hjq.http.model.HttpMethod;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.easyNet.bean.ID;
import com.smh.szyproject.easyNet.bean.User;
import com.smh.szyproject.easyNet.model.HttpData;
import com.smh.szyproject.easyNet.request.LoginApi;
import com.smh.szyproject.easyNet.request.UpdateImageApi;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.ExecutorsPool.LunZiAppThreadManager;

import java.io.File;
import java.util.List;

import okhttp3.Call;

/**
 * author : smh
 * date   : 2020/10/16 11:42
 * desc   :
 */
public class EasyHttpActivity extends BaseActivity implements OnHttpListener {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
    @Override
    public void init(Bundle savedInstanceState) {
        findViewById(R.id.tv_next).setOnClickListener((View v) -> {
//            get();
//            post();
            //上面那俩是异步的
            //下面是同步的
//            L.e("111");
//            syncRequest();
//
//            //上传文件
//            upload();

            //测试没问题啊啊啊啊啊
            downLoad();

            //使用helper
            EasyHttpHelper.test(this,new User("admin"),this);

        });
    }

    private void downLoad() {
        EasyHttp.download(this)
                .method(HttpMethod.GET)
                .file(new File(Environment.getExternalStorageDirectory(), "微信.apk"))
                //.url("https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk")
                .url("http://dldir1.qq.com/weixin/android/weixin708android1540.apk")
                .md5("2E8BDD7686474A7BC4A51ADC3667CABF")
                .listener(new OnDownloadListener() {

                    @Override
                    public void onStart(File file) {
                        showProgressHUD();
//                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onProgress(File file, long totalByte, long downloadByte, int progress) {
//                        mProgressBar.setProgress(progress);
                    }

                    @Override
                    public void onComplete(File file) {
                        showToast("下载完成：" + file.getPath());
                        installApk(EasyHttpActivity.this, file);
                    }

                    @Override
                    public void onError(File file, Exception e) {
                        showToast("下载出错：" + e.getMessage());
                    }

                    @Override
                    public void onEnd(File file) {
                        dismissProgressHUD();
                    }

                }).start();
    }

    private void upload() {

        File file = new File(Environment.getExternalStorageDirectory(), getString(R.string.app_name) + ".png");
        // 生成图片到本地
//        drawableToFile(ContextCompat.getDrawable(this, R.drawable.ic_launcher), file);

        //假代码，不可用
        EasyHttp.post(this)
                .api(new UpdateImageApi(file))
                .request(new OnUpdateListener<Void>() {

                    @Override
                    public void onStart(Call call) {
                    }

                    @Override
                    public void onUpdate(long totalByte, long updateByte, int progress) {
//                        mProgressBar.setProgress(progress);
                    }

                    @Override

                    public void onSucceed(Void result) {
                        showToast("上传成功");
                    }

                    @Override
                    public void onFail(Exception e) {
                        showToast("上传失败");
                    }

                    @Override
                    public void onEnd(Call call) {

                    }
                });

    }

    private void syncRequest() {
        LunZiAppThreadManager.getInstance().execute(()->{
            try {
                ID data = EasyHttp.post(this).api(new LoginApi().setAuthor(new User("admin"))).execute(new DataClass<ID>() {});
                L.e("" + data.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        new Thread(() -> {
            runOnUiThread(this::showProgressHUD);
            try {
                L.e("???");
                ID data = EasyHttp.post(this).api(new LoginApi()
                        .setAuthor(new User("admin"))).execute(new DataClass<ID>() {
                });
                L.e("" + data.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                dismissProgressHUD();
            });
        }).start();
    }

    private void post() {
        EasyHttp.post(this).api(new LoginApi().setAuthor(new User("admin"))).request(new HttpCallback<ID>(this) {
        });
    }

    private void get() {

//        EasyHttp.get(this).api(new LoginApi().setAuthor(new User("admin")))
//                .request(new HttpCallback<HttpData<ID>>(this){
//                    @Override
//                    public void onSucceed(HttpData<ID> result) {
//                        super.onSucceed(result);
//                        L.e("成功:"+result.getMessage());
//                    }
//
//                    @Override
//                    public void onFail(Exception e) {
//                        super.onFail(e);
//                    }
//                });

        //实现了OnHttpListener就不用再实现实现下面的onSuccess方法了
        EasyHttp.get(this).api(new LoginApi().setAuthor(new User("admin"))).request(new HttpCallback<ID>(this) {});

        //如果外面套了一层HttpData，自己改，就加一个HttpData
        EasyHttp.get(this).api(new LoginApi().setAuthor(new User("admin"))).request(new HttpCallback<HttpData<ID>>(this) {});


//        EasyHttp.get(this).api(new LoginApi().setAuthor(new User("admin")))
//                .request(new HttpCallback<ID>(this){
//                    @Override
//                    public void onSucceed(ID result) {
//                        super.onSucceed(result);
//                        L.e("成功:"+result.getId());
//                    }
//
//                    @Override
//                    public void onFail(Exception e) {
//                        super.onFail(e);
//                    }
//                });
    }


    @Override
    public void onSucceed(Object result) {
        ID id = (ID) result;
        L.e("我是123 id：" + id.getId());
    }

    @Override
    public void onFail(Exception e) {

    }


    /**
     * 安装 Apk installApk
     * <p>
     * 先试试参考，看行不行
     */
    private void installApk(final Context context, final File file) {
        XXPermissions.with(EasyHttpActivity.this)
                // 安装包权限
                .permission(Permission.REQUEST_INSTALL_PACKAGES)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            Uri uri;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            } else {
                                uri = Uri.fromFile(file);
                            }

                            intent.setDataAndType(uri, "application/vnd.android.package-archive");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean quick) {

                    }
                });
    }

}
