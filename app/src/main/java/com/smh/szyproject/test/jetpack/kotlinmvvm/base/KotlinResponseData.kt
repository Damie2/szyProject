package com.smh.szyproject.test.jetpack.kotlinmvvm.base

/**
 * actor 晴天 create 2019/5/31
 * 响应结果数据
 */
data class KotlinResponseData<out T>(val errorCode: Int, val errorMsg: String, val data: T)