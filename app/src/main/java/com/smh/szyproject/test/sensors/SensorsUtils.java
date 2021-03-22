package com.smh.szyproject.test.sensors;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 神策埋点工具类
 */
public class SensorsUtils {

    private static volatile SensorsUtils sInstance;

    public static JSONObject properties ;

    public static SensorsUtils with(){
        if(sInstance==null){
            synchronized (ActivityManager.class){
                if(sInstance==null){
                    sInstance = new SensorsUtils();
                }
            }
        }
        properties = new JSONObject();
        return sInstance;
    }


    public SensorsUtils setProperties(String key,String value)  {
        try {
            properties.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SensorsUtils setProperties(String key,boolean value)  {
        try {
            properties.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SensorsUtils setProperties(String key,long value)  {
        try {
            properties.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SensorsUtils setProperties(String key,double value)  {
        try {
            properties.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SensorsUtils setProperties(String key,Object value)  {
        try {
            properties.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SensorsUtils setProperties(String key,int value)  {
        try {
            properties.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置事件公共属性
     * 将应用名称作为事件公共属性，后续所有 track() 追踪的事件都会自动带上 key的属性
     * @param key
     * @param value
     */
    public void registerSuperBuild(String key,String value) {
        try {
            properties.put(key, value);
            SensorsDataAPI.sharedInstance().registerSuperProperties(properties);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void build(Context mContext,String key) {
        if ( mContext == null || !(mContext instanceof Activity)) {
            Toast.makeText(mContext,"context异常",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            //在这添加公共属性,下面是demo
            final PackageManager manager = mContext.getPackageManager();
            final PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            properties.put("className",mContext.getClass().getName());
            properties.put("$app_version", info.versionName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        SensorsDataAPI.sharedInstance().track(key, properties);
    }

    /**
     * 添加时间自定义属性
     * @param key
     */
    public void build(String key) {
        SensorsDataAPI.sharedInstance().track(key, properties);
    }
}
