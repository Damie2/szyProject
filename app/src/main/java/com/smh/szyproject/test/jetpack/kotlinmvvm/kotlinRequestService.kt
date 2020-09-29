package com.smh.szyproject.test.jetpack.kotlinmvvm

import com.smh.szyproject.test.jetpack.kotlinmvvm.base.KotlinResponseData
import com.smh.szyproject.test.jetpack.kotlinmvvm.databean.Data
import retrofit2.http.GET

/**
 * author : smh
 * date   : 2020/9/21 11:18
 * desc   :
 */
interface kotlinRequestService {
    @GET("wxarticle/chapters/json")
    suspend fun getDatas():KotlinResponseData<List<Data>>
}