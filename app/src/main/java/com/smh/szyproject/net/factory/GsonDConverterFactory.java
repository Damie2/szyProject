package com.smh.szyproject.net.factory;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class GsonDConverterFactory extends Converter.Factory{

//    public static GsonDConverterFactory create() {
//        return create(new Gson());
//    }
//
//    public static GsonDConverterFactory create(Gson gson) {
//        return new GsonDConverterFactory(gson);
//    }
//
//    private final Gson gson;
//
//    private GsonDConverterFactory(Gson gson) {
//        if (gson == null) throw new NullPointerException("gson == null");
//        this.gson = gson;
//    }
//
//    @Override public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        return new GsonResponseBodyConverter <>(gson, type);
//    }
//
//    @Override public Converter < ?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//        TypeAdapter< ?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new GsonRequestBodyConverter < >(gson, adapter);
//    }
//
//
//    final class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
//        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
//        private static final Charset UTF_8 = Charset.forName("UTF-8");
//
//        private final Gson gson;
//        private final TypeAdapter<T> adapter;
//
//        GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//            this.gson = gson;
//            this.adapter = adapter;
//        }
//
//        @Override
//        public RequestBody convert(T value) throws IOException {
//            Buffer buffer = new Buffer();
//            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
//            JsonWriter jsonWriter = gson.newJsonWriter(writer);
//            adapter.write(jsonWriter, value);
//            jsonWriter.close();
//            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
//        }
//    }
//
//    final class GsonResponseBodyConverter < T > implements Converter< ResponseBody,
//            T > {
//        private final Gson gson;
//        private final Type type;
//
//        GsonResponseBodyConverter(Gson gson, Type type) {
//            this.gson = gson;
//            this.type = type;
//        }
//
//        /**
//         * 针对数据返回成功、错误不同类型字段处理
//         */
//        @Override public T convert(ResponseBody value) throws IOException {
//            String response = value.string();
//            try {
//                // 这里的type实际类型是 LoginUserEntity<User>  User就是user字段的对象。
//                LoginUserEntity result = gson.fromJson(response, LoginUserEntity.class);
//                int code = result.getStatus();
//                if (code == 200) {
//                    return gson.fromJson(response, type);
//                } else {
//                    Log.d("HttpManager", "err==：" + response);
//                    LoginUserErrorEntity errResponse = gson.fromJson(response, LoginUserErrorEntity.class);
//                    if (code == 410) {
//                        throw new ResultException(errResponse.getErrorMessage(), code);
//                    } else {
//                        throw new ResultException(errResponse.getErrorMessage(), code);
//                    }
//                }
//            } finally {
//                value.close();
//            }
//        }
//    }

    //https://blog.csdn.net/u013214588/article/details/80568900

}



