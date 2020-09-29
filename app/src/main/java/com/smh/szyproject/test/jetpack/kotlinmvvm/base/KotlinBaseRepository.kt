package com.smh.szyproject.test.jetpack.kotlinmvvm.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * actor 晴天 create 2019/5/31
 */
open class KotlinBaseRepository {
//加open，子类就可以继承父类并重写父类方法
    suspend fun <T : Any> request(call: suspend () -> KotlinResponseData<T>): KotlinResponseData<T> {
        return withContext(Dispatchers.IO) { call.invoke() }.apply {
            //这儿可以对返回结果errorCode做一些特殊处理，比如token失效等，可以通过抛出异常的方式实现
            //例：当token失效时，后台返回errorCode 为 100，下面代码实现,再到baseActivity通过观察error来处理
            when (errorCode) {
                100 -> throw TokenInvalidException()
            }
        }
    }

    class TokenInvalidException(msg: String = "token invalid") : Exception(msg)
}