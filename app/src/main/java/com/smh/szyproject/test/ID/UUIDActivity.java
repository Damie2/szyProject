package com.smh.szyproject.test.ID;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.PhoneInfo;
import com.smh.szyproject.test.mvvm.newMvvm;

import java.util.UUID;

/**
 * author : smh
 * date   : 2020/11/25 10:00
 * desc   :
 */
public class UUIDActivity extends BaseActivity {

    String imei;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        String uniqueId = UUID.randomUUID().toString();//java写法

        if (Build.VERSION.SDK_INT < 29) {
            imei = PhoneInfo.getIMEI(UUIDActivity.this);
        } else {
            imei = Settings.System.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }

        L.e("" + imei);

        //或者拼接


//        AndroidId : 如：df176fbb152ddce,无需权限,极个别设备获取不到数据或得到错误数据；
//        serial：如：LKX7N18328000931,无需权限,极个别设备获取不到数据；
//        IMEI : 如：23b12e30ec8a2f17，需要权限；
//        Mac: 如：6e:a5:....需要权限，高版本手机获得数据均为 00:20:00:00:00
//        Build.BOARD  如：BLA  主板名称,无需权限,同型号设备相同
//        Build.BRAND  如：XIAOMI  厂商名称,无需权限,同型号设备相同
//        Build.HARDWARE  如: msm8996  硬件名称,无需权限,同型号设备相同
    }
}
