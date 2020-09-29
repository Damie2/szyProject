package com.smh.szyproject.test.jetpack.kotlinmvvm.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author : smh
 * date   : 2020/9/18 15:24
 * desc   :
 */
abstract class KotlinBaseRetrofitClient {
    fun <s> getService(service: Class<s>, baseUrl: String): s {

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(service)
    }
}