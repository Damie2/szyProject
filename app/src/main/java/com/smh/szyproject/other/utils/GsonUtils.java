package com.smh.szyproject.other.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.gson.factory.GsonFactory;

/**
 * 获取gson单例
 * 使用轮子的Json解析容错框架
 */
public class GsonUtils {
//    private static Gson gson;
//    public static Gson getGson() {
//        if (gson==null)
//            gson=new Gson();
//        return gson;
//    }

    public static Gson getGson() {
        return GsonFactory.getSingletonGson();
    }

    public static GsonBuilder getGsonBuilder() {
        return GsonFactory.createGsonBuilder();
    }

}
