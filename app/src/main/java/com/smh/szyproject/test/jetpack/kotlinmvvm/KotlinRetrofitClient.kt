package com.smh.szyproject.test.jetpack.kotlinmvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author : smh
 * date   : 2020/9/21 11:16
 * desc   :
 */
object KotlinRetrofitClient{
    val BASE_URL  = "https://wanandroid.com/"
    val reqApi by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(kotlinRequestService::class.java)
                                            //::表示把一个方法当做一个参数，传递到另一个方法中进行使用
    }
}