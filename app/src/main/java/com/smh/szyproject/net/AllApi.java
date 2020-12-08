package com.smh.szyproject.net;


import com.google.gson.JsonObject;
import com.smh.szyproject.mvp.bean.CallBean;
import com.smh.szyproject.mvp.bean.CallResult;
import com.smh.szyproject.mvp.bean.ID;
import com.smh.szyproject.mvp.bean.Test;
import com.smh.szyproject.mvp.bean.TestBean;
import com.smh.szyproject.mvp.bean.User;
import com.smh.szyproject.mvp.bean.getName;
import com.smh.szyproject.mvp.bean.sendId;
import com.smh.szyproject.mvp.bean.testStatus;
import com.smh.szyproject.test.mvvm.PhoneParameter;
import com.smh.szyproject.test.mvvm.Result;
import com.smh.szyproject.test.netTest.Bean2;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.jmessage.support.okhttp3.MultipartBody;
import cn.jmessage.support.okhttp3.RequestBody;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;


public interface  AllApi {

    @POST(ApiAddress.sendVersion)
    Observable<Object> test1(@Body Test parameter);

    @POST(ApiAddress.sendVersion)
    @FormUrlEncoded
    Observable<TestBean> test2(@FieldMap HashMap<String, Object> map);

    @POST(ApiAddress.sendVersion)
    Observable<JSONObject> test(@Body Test parameter);


    @POST(ApiAddress.getStatus)
    Observable<JSONObject> testStatus(@Body testStatus parameter);

    @POST(ApiAddress.getResult)
    Observable<Bean2> testStatus(@Body Test parameter);

    //1.安装APP后发送请求
    @POST(ApiAddress.sendParameter)
    Observable<Result> sendParameter(@Body PhoneParameter parameter);

    @POST(ApiAddress.getName)
    Observable<getName> sendID(@Body sendId parameter);


    //call登录的
    @POST(ApiAddress.callLogin)
    Observable<ID> callLogin(@Body User user);

    @POST(ApiAddress.callGetUser)
    Observable<CallBean> callGetUser(@Body ID user);

    @Multipart
    @POST(ApiAddress.callSendResult)
    Observable<JSONObject> callSendResult(@PartMap Map<String,RequestBody> params,  @Body CallResult result );




    @POST("users/{user}/repos")//path的意思是 如果user为空，就把path的这个user，放到上面post的这个user里
    Observable<JSONObject> listRepos(@Path("user") String user);



//    雨刮器
//    紫桶、机滤、空滤
//    节气门、发动机清洗


    //看这里
//    https://www.jianshu.com/p/74b7da380855

//    @POST("login")
//    @FormUrlEncoded
//    Observable<Bean2> login(@Field("username") String username, @Field("password") String password);


//    @POST("login")
//    @FormUrlEncoded
//    Call<User> login(@Field("username") String username, @Field("password") String password);


//    一般的传参，我们可以通过@Query
//    @GET("users")
//    Call<List<User>> getUsersBySort(@Query("sortby") String sort);


//    表单的方式传递键值对
//    @POST("login")
//    @FormUrlEncoded
//    Call<User> login(@Field("username") String username, @Field("password") String password);

//============================
    //单文件上传@Multipart
//    File file = new File(Environment.getExternalStorageDirectory(), "icon.png");
//    RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
//    MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", "icon.png", photoRequestBody);
//    Call<User> call = userBiz.registerUser(photo, RequestBody.create(null, "abc"), RequestBody.create(null, "123"));

//    @Multipart
//    @POST("register")
//    Call<Bean2> registerUser(@Part MultipartBody.Part photo, @Part("username") RequestBody username, @Part("password") RequestBody password);

//    单文件上传@Multipart


//    或者
//    @Multipart
//    @POST ("/api/Accounts/editaccount")
//    Call<User> editUser (@Header("Authorization") String authorization, @Part("file\"; filename=\"pp.png") RequestBody file , @Part("FirstName") RequestBody fname, @Part("Id") RequestBody id);
    //============================

    //===========多文件上传=================
//    File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
//    RequestBody photo = RequestBody.create(MediaType.parse("image/png", file);
//    Map<String,RequestBody> photos = new HashMap<>();
//photos.put("photos\"; filename=\"icon.png", photo);
//photos.put("username",  RequestBody.create(null, "abc"));
//
//    Call<User> call = userBiz.registerUser(photos, RequestBody.create(null, "123"));

    //===========多文件上传=================


    //===========下载文件=================
//    @GET("download")
//    Call<ResponseBody> downloadTest();
//
//    Call<ResponseBody> call = userBiz.downloadTest();
//call.enqueue(new Callback<ResponseBody>()
//    {
//        @Override
//        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
//        {
//            InputStream is = response.body().byteStream();
//            //save file
//        }
//
//        @Override
//        public void onFailure(Call<ResponseBody> call, Throwable t)
//        {
//
//        }
//    });

    //===========下载文件=================

}
