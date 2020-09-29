package com.smh.szyproject.net.factory;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.smh.szyproject.MyApplication;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.ToastUtils;

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

    private static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ToastUtils.showToastForText(MyApplication.getContext(), msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };


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
            String body = value.string();
            L.e("返回response:" + body);
            JSONObject json = new JSONObject(body);
            int code = json.optInt("code");
            if (code == 200) {
                body =json.optString("data");
                return adapter.fromJson(body);
            }else if(code==0){
                Message message = new Message();
                message.obj = json.optString("msg");
                message.what = 0;
                handler.sendMessage(message);
                throw new RuntimeException();
            }else{
                //正常就走这里
                body =  json.optString("data");
                return adapter.fromJson(body);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }



//    @Override
//    public T convert(ResponseBody value) throws IOException {
//        //https://www.jianshu.com/p/ad9bda26539b   判断code这个也好 处理  { "code": 0, "message": "success", "data": { "commentNum": 2, "zanNum": 0 } }
//        //https://blog.csdn.net/ww55555577/article/details/60777511?utm_source=itdadao&utm_medium=referral
//        try {
//            //在这里搞解密 统一处理
//            //参考HandleFuc
////            String body = value.string();
//            // 在这里搞解密之类的
////            String body = EncryptUtils.decryptParams(originalBody);
//
////            String body = "{ \"code\": 2, \"message\": \"success\", \"data\": { \"commentNum\": 2, \"zanNum\": 0 } }";
//            String body = "{ \"code\": 1, \"message\": \"error\", \"data\": {} }";
////            String body = "{ \"code\": 0, \"message\": \"success\", \"data\": { \"commentNum\": 2, \"zanNum\": 0 } }";
//            L.e("body:" + body);
//            JSONObject json = new JSONObject(body);
//            int code = json.optInt("code");
//            if (code == 0) {
//                L.e("0");
//                //如果是空的，就放个空的data
//                json.put("data", new JSONObject());
//                body = json.toString();
//
//                return adapter.fromJson(body);
//            }else if(code==1){
//                L.e("11");
//                Message message = new Message();
//                message.obj = body;
//                message.what = 1;
//                handler.sendMessage(message);
//                throw new RuntimeException();
//            }else{
//                //正常就走这里
//                body =  json.optString("data");
//                return adapter.fromJson(body);
//            }
//
////            {"code":200,"data":{"id":1},"msg":""}
////            {"code":0,    "data":"",       "msg":"\u6ca1\u6709\u8be5\u7528\u6237"}
//
////            String code = json.getString("resultCode");
////            String msg = json.getString("resultInfo");
////            if (code.equals("0000")) {
////                return mGson.fromJson(body, mType);
////            } else {
////                Test test = mGson.fromJson(body, Test.class);
////                return (T) test;
////            }
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        } finally {
//            value.close();
//        }
//    }

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
