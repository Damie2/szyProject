package com.smh.szyproject.test.jetpack.security;

import android.os.Bundle;
import android.os.Environment;
import android.security.keystore.KeyGenParameterSpec;
import android.view.View;

import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;

import com.hjq.permissions.Permission;
import com.smh.szyproject.R;
import com.smh.szyproject.aop.Permissions;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.SPUtil;
import com.smh.szyproject.other.utils.utilCode.EncryptUtils;
import com.smh.szyproject.test.TestActivity;
import com.smh.szyproject.test.observer.activity.SecondActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;

import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/9/17 11:18
 * desc   :
 */
public class javaSecurity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }
    @Permissions(Permission.MANAGE_EXTERNAL_STORAGE)
    @Override
    public void init(Bundle savedInstanceState) {

    }

    //读取加密的文件
    private void read() {
        //256加密
        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            EncryptedFile encryptedFile = new EncryptedFile.Builder(
                    new File(Environment.getExternalStorageDirectory() + "/" + "Security.txt"),
                    getApplicationContext(),
                    masterKeyAlias,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();
            InputStream inputStream = encryptedFile.openFileInput();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int nextByte = inputStream.read();

            while (nextByte != -1) {
                byteArrayOutputStream.write(nextByte);
                nextByte = inputStream.read();
            }
            byte[] plaintext = byteArrayOutputStream.toByteArray();
            //输出 我是内容:哈哈哈  ok
            L.e("我是内容:"+new String(plaintext));//ok

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //写入加密的文件
    private void write() {
        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            EncryptedFile encryptedFile = new EncryptedFile.Builder(new File(Environment.getExternalStorageDirectory() + "/" + "Security.txt")
                    , getApplicationContext()
                    , masterKeyAlias
                    , EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();

            byte[] fileContent = "哈哈哈".getBytes(StandardCharsets.UTF_8);
            OutputStream outputStream = encryptedFile.openFileOutput();
            outputStream.write(fileContent);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
//                read();
//                write();


                startActivity(TestActivity.class);
//                    L.e("1"+System.currentTimeMillis());
//                    SPUtil.putEncryptedString("name","邵民航",this);
//                    L.e(""+SPUtil.getEncryptedString("name","",this));
//                    L.e("2"+System.currentTimeMillis());
//                    //942毫秒
//                    SPUtil.putString("name1","aaa",this);
//                    L.e(""+SPUtil.getString("name1","",this));
//                    L.e("3"+System.currentTimeMillis());
//                    //6毫秒

                break;
        }
    }
}
