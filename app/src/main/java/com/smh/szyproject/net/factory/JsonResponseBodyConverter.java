package com.smh.szyproject.net.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.smh.szyproject.utils.L;

import java.io.IOException;

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

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        L.e("响应的的串是:" + responseBody.string());
        return null;
    }

//    /**
//     * 转换
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
