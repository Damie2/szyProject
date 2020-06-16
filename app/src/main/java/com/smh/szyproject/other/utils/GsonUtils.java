package com.smh.szyproject.other.utils;


import com.google.gson.Gson;

/**
 * 获取gson单例
 * Created by maoxiaofei on 2016/4/13.
 */
public class GsonUtils {
    private static Gson gson;
    public static Gson getGson() {
        if (gson==null)
            gson=new Gson();
        return gson;
    }

}
