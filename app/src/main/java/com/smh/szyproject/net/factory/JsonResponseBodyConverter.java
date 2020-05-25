package com.smh.szyproject.net.factory;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.smh.szyproject.bean.Test;
import com.smh.szyproject.bean.getStatusResult;
import com.smh.szyproject.utils.L;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author : smh
 * date   : 2020/5/7 10:18
 * desc   : 接收解密
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson mGson;//gson对象
    private final TypeAdapter<T> adapter;
    private Type mType;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type mType) {
        this.mGson = gson;
        this.adapter = adapter;
        this.mType = mType;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        try {
            //参考HandleFuc
            String body = value.string();
            JSONObject json = new JSONObject(body);
            String code = json.optString("code");
            if (TextUtils.equals(code,"200")) {
//                getStatusResult test = mGson.fromJson(body, getStatusResult.class);
                //返回共用的，然后那边用Object接收
                return (T) json;
            } else {
                return mGson.fromJson(body, mType);
            }


//            String code = json.getString("resultCode");
//            String msg = json.getString("resultInfo");
//            if (code.equals("0000")) {
//                return mGson.fromJson(body, mType);
//            } else {
//                Test test = mGson.fromJson(body, Test.class);
//                return (T) test;
//            }


        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }

//    @Override
//    public T convert(ResponseBody responseBody) throws IOException {
//        L.e("响应的的串是:" + responseBody.string());
//        return null;
//    }

//    /**
//     * 转换   这个是解密过程
//     *
//     * @param responseBody
//     * @return
//     * @throws IOException
//     */
//    @Override
//    public T convert(ResponseBody responseBody) throws IOException {
////        String response = responseBody.string();
////        String strResult = response.substring(1, response.length() - 1);
////        String result = XXTEA.Decrypt(strResult, HttpConstant.KEY);//解密
////        L.e("解密的服务器数据：" + result);
////        PageBean pageBean = mGson.fromJson(result, PageBean.class);
////        return (T) pageBean;
//
//
////      上面是demo
//        String response = responseBody.string();
//        L.e("相应的串是:" + response);
//        return (T) response;
//    }
}
