package com.smh.szyproject.net.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author : smh
 * date   : 2020/5/7 10:16
 * desc   :
 */
public class JsonConverterFactory extends Converter.Factory {
    public static JsonConverterFactory create() {
        return create(new Gson());
    }

    public static JsonConverterFactory create(Gson gson) {
        return new JsonConverterFactory(gson);

    }

    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonResponseBodyConverter<>(gson, adapter,type); //响应
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(gson, adapter); //请求
    }

}
