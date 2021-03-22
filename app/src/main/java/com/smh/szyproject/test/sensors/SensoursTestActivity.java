package com.smh.szyproject.test.sensors;

import android.os.Bundle;

import com.sensorsdata.analytics.android.sdk.SAConfigOptions;
import com.sensorsdata.analytics.android.sdk.SensorsAnalyticsAutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

public class SensoursTestActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //Application里面添加

        //https://manual.sensorsdata.cn/sa/latest/tech_sdk_client_android_basic-17563956.html
        initSensours();
        //设置公共属性
        SensorsUtils.with().registerSuperBuild("publicEvent","123");

        SensorsUtils.with()
                .setProperties("key","value")
                .setProperties("e",1)
                .build("");

    }

    private void initSensours() {
        String SA_SERVER_URL = "数据接收地址";

        // 初始化配置
        SAConfigOptions saConfigOptions = new SAConfigOptions(SA_SERVER_URL);
        // 开启全埋点
        saConfigOptions.setAutoTrackEventType(SensorsAnalyticsAutoTrackEventType.APP_CLICK |
                SensorsAnalyticsAutoTrackEventType.APP_START |
                SensorsAnalyticsAutoTrackEventType.APP_END |
                SensorsAnalyticsAutoTrackEventType.APP_VIEW_SCREEN)
                //开启 Log
                .enableLog(true);
        /**
         * 其他配置，如开启可视化全埋点
         */
        // 需要在主线程初始化神策 SDK
        SensorsDataAPI.startWithConfigOptions(this, saConfigOptions);
    }
}
