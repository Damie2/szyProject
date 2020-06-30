package com.smh.szyproject.test.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.utilCode.FileUtils;

import java.io.File;
import java.util.Date;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/6/9 10:53
 * desc   : 相机拍照开发
 *
 *   拍照获取缩略图除了拍照权限外不需要别的权限
 */
public class CameraActivityTwo extends BaseActivity {
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 0x1;
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
                Intent intent;
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "选择图片"),
                        REQUEST_CODE_GETIMAGE_BYSDCARD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_GETIMAGE_BYSDCARD) {
            Uri dataUri = data.getData();
            if (dataUri != null) {
                File file = FileUtils.uri2File(CameraActivityTwo.this, dataUri);
                L.e("图片的绝对路径是:"+ file.getAbsolutePath());
            }
        }
    }





}
