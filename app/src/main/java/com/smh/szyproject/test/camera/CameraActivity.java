package com.smh.szyproject.test.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/9 10:53
 * desc   : 相机拍照开发
 *
 *   拍照获取缩略图除了拍照权限外不需要别的权限
 */
public class CameraActivity extends BaseActivity {

    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.btn_start)
    Button btn_start;
    private int REQ_CODE = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_camera_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Permissions(Permission.CAMERA)
            @Override
            public void onClick(View v) {
                    L.e("点击拍照");
                    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//用来打开相机的Intent
                    if(takePhotoIntent.resolveActivity(getPackageManager())!=null){//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
                        startActivityForResult(takePhotoIntent,REQ_CODE);//启动相机
                    }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE&&resultCode==RESULT_OK){
            /*缩略图信息是储存在返回的intent中的Bundle中的，
             * 对应Bundle中的键为data，因此从Intent中取出
             * Bundle再根据data取出来Bitmap即可*/
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            iv_photo.setImageBitmap(bitmap);
        }
    }
}
