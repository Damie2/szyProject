package com.smh.szyproject.net.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.smh.szyproject.other.utils.L;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * author : smh
 * date   : 2020/5/7 10:18
 * desc   : 请求加密
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    //    @Override
//    public RequestBody convert(T value) throws IOException {
//        L.e("request中传递的json数据：" + GsonUtils.getGson().toJson(value));
//        return null;
//    }
    @Override
    public RequestBody convert(T value) throws IOException {
        String postBody = gson.toJson(value); //对象转化成json
        L.e("请求的数据：" + postBody);
        return RequestBody.create(MEDIA_TYPE, postBody);
    }
//
//    @Override
//    public RequestBody convert(T value) throws IOException {
//        //加密
////        APIBodyData data = new APIBodyData();
////        L.e("request中传递的json数据：" + value.toString());
////        data.setData(XXTEA.Encrypt(value.toString(), HttpConstant.KEY));
////        String postBody = gson.toJson(data); //对象转化成json
////        L.e("转化后的数据：" + postBody);
////        return RequestBody.create(MEDIA_TYPE, postBody);
//
////      上面是demo
//        L.e("request中传递的json数据：" + value.toString());
//        data.setData(XXTEA.Encrypt(value.toString(), HttpConstant.KEY));
//        String postBody = gson.toJson(data); //对象转化成json
//        L.e("转化后的数据：" + postBody);
//        return RequestBody.create(MEDIA_TYPE, postBody);
//
//    }

}
