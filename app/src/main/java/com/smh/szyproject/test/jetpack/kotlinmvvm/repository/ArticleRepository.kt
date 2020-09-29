package com.smh.szyproject.test.jetpack.kotlinmvvm.repository

import com.smh.szyproject.test.jetpack.kotlinmvvm.KotlinRetrofitClient
import com.smh.szyproject.test.jetpack.kotlinmvvm.base.KotlinBaseRepository
import com.smh.szyproject.test.jetpack.kotlinmvvm.base.KotlinResponseData
import com.smh.szyproject.test.jetpack.kotlinmvvm.databean.Data

/**
 * author : smh
 * date   : 2020/9/21 11:02
 * desc   :
 */
class ArticleRepository :KotlinBaseRepository(){
    //suspend  挂起函数，并不会阻塞线程
    suspend fun getDatas(): KotlinResponseData<List<Data>> = request {
        KotlinRetrofitClient.reqApi.getDatas()
    }

}

