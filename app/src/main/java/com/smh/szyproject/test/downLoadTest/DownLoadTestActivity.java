package com.smh.szyproject.test.downLoadTest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/6/1 13:32
 * desc   :
 */
public class DownLoadTestActivity extends BaseActivity implements View.OnClickListener {

//    private String path = "http://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fimg.redocn.com%2Fsheying%2F20150421%2Flantiandahai_4201568.jpg&thumburl=http%3A%2F%2Fimg5.imgtn.bdimg.com%2Fit%2Fu%3D1734872503%2C344259658%26fm%3D26%26gp%3D0.jpg";

    private String path = "https://down.360safe.com/360ap/360freeap_beta_setup_freewifi.exe";
    private String saveFilePath;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_down_load_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        /**
         * 新下载工具，在package com.smh.szyproject.other.utils.utilCode;下面的FileUtil的工具里
         */


        Aria.download(this).register();
        String fileName = System.currentTimeMillis() + ".exe";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

//            https://blog.csdn.net/Kelaker/article/details/80471352

//            1.内存
//            2.内存设备--->应用名--->（cache/files/其他名字）：用的少
//            3.内存设备--->Android--->data--->应用名--->（cache/files/其他名字）：用的多
//            4.sd卡--->应用名--->（files/cache/其他名字）：用得少
//            5.sd卡--->Android--->data--->应用名--->（cache/files/其他名字）：用的多

            /**
             *            {@link android.os.Environment#DIRECTORY_MUSIC},
             *             {@link android.os.Environment#DIRECTORY_PODCASTS},
             *             {@link android.os.Environment#DIRECTORY_RINGTONES},
             *             {@link android.os.Environment#DIRECTORY_ALARMS},
             *             {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
             *             {@link android.os.Environment#DIRECTORY_PICTURES}, or
             *             {@link android.os.Environment#DIRECTORY_MOVIES}.
             */


            saveFilePath = MyApplication.getContext().getExternalFilesDir("file") + fileName;
        } else {
            saveFilePath = Environment.getExternalStorageDirectory() + "/download/" + fileName;
        }
        L.e("保存路径：" + saveFilePath);
        lunzi();
    }

    /**
     * 函数返回路径/storage/emulated/0/Android/data/包名/files
     *
     * 用来存储一些长时间保留的数据,应用卸载会被删除
     * @param context
     * @return
     */
    public String getFilesPath( Context context ){
        String filePath ;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            filePath = context.getExternalFilesDir(null).getPath();
        }else {
            //外部存储不可用
            filePath = context.getFilesDir().getPath() ;
        }
        return filePath ;
    }




    /**
     *
     * 函数返回路径/storage/emulated/0/Android/data/包名/cache
     *
     * 用来存储一些临时缓存数据
     *
     *
     *
     *   getExternalCacheDir和getCacheDir
     *   相同点：
     *
     *          1. 都可以做app缓存目录。
     *
     *          2. app卸载后，两个目录下的数据都会被清空。
     *
     * 不同点:
     *
     *          1、目录的路径不同。前者的目录存在外部SD卡上的。后者的目录存在app的内部存储上。
     *
     *          2、前者的路径在手机里可以直接看到。后者的路径需要root以后，用Root Explorer 文件管理器才能看到。
     * @param context
     * @return
     */
    public String getCachePath( Context context ){
        String cachePath ;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = context.getExternalCacheDir().getPath() ;
        }else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() ;
        }
        return cachePath ;
    }


    public static String getSDCardPicPath() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            return MyApplication.getContext().getExternalFilesDir("file").getPath() ;
//            return MyApplication.getContext().getExternalFilesDir(AppConstants.SHARE_DOWNLOAD_PIC_FILE).getPath() ;
        }
//        return Environment.getExternalStorageDirectory() + File.separator + AppConstants.SHARE_DOWNLOAD_PIC_FILE;
        return Environment.getExternalStorageDirectory() + File.separator + "file";
    }

    private void lunzi() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        File sourceFile = new File("imagePath");
        String fileName = "CROP_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) +
                "." + getImageFormat(sourceFile).toString().toLowerCase();
        String subFolderName = "CropImage";
        Uri outputUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 适配 Android 10 分区存储特性
            ContentValues values = new ContentValues();
            // 设置显示的文件名
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            // 设置输出的路径信息
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_DCIM + File.separator + subFolderName);
            // 生成一个新的 uri 路径
            outputUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        } else {
            File folderFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + subFolderName);
            if (!folderFile.isDirectory()) {
                folderFile.delete();
            }
            if (!folderFile.exists()) {
                folderFile.mkdirs();
            }
            outputUri = Uri.fromFile(new File(folderFile, fileName));
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        // 设置裁剪后保存的文件格式
        intent.putExtra("outputFormat", getImageFormat(sourceFile).toString());
    }

    /**
     * 获取图片文件的格式
     */
    private static Bitmap.CompressFormat getImageFormat(File file) {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".png")) {
            return Bitmap.CompressFormat.PNG;
        } else if (fileName.endsWith(".webp")) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
    @OnClick(R.id.tv_down_load)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_down_load) {
            L.e("点击下载");
            downLoad();
        }
    }


    private void downLoad() {
//        method1();//xutils
//        method2();//okhttp的
//        method4();//Retrofit
//        method5();//多文件下载
        call();

        //https://github.com/hongyangAndroid/okhttputils
        //鸿洋的okhttputils 也可以用
    }

    @Permissions({Permission.CALL_PHONE})
    public void call() {
        L.e("走了？");
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }


    //多文件下载
    private void method5() {
        long taskId = Aria.download(this)
                .load(path)     //读取下载地址
                .setFilePath(saveFilePath) // 设置文件保存路径
                .create();


//        Aria.download(this)
//                .load(taskId)     //读取任务id
//                .stop();       // 停止任务
//        //.resume();    // 恢复任务

    }
    //下面这两个是Aria的方法，直接贴上就行，下载的时候，会回调这俩方法

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        if (task.getKey().equals(path)) {
//            可以通过url判断是否是指定任务的回调
        }
        int p = task.getPercent();    //任务进度百分比
        L.e("进度是：" + p);
//        String speed = task.getConvertSpeed();    //转换单位后的下载速度，单位转换需要在配置文件中打开
//        L.e("速度:" + speed);
//        String speed1 = task.getSpeed(); //原始byte长度速度
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        L.e("文件的长度是:" + task.getFileSize());
    }

    //上面这两个是Aria的方法，直接贴上就行，下载的时候，会回调这俩方法

    //Xutils不带进度条的
    private void method4() {
        RequestParams params = new RequestParams("https://jiaoliu168.com/api/room/stock");
        params.addParameter("page", 1);
        params.addParameter("size", 15);
        try {
            x.http().post(params, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {
                    L.e("结果是:" + result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    L.e("onError");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    L.e("onCancelled");
                }

                @Override
                public void onFinished() {
                    L.e("onFinished");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


//        try {
//            String result = x.http().postSync(params, String.class);
//            L.e("结果是:" + result);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
    }


    //okhttp的
    private void method2() {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }
}
