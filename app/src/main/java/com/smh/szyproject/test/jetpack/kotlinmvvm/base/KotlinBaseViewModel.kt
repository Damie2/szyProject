package com.smh.szyproject.test.jetpack.kotlinmvvm.base

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception

/**
 * author : smh
 * date   : 2020/9/18 15:30
 * desc   :
 */
open class KotlinBaseViewModel:ViewModel(), LifecycleObserver{

    private val error by lazy { MutableLiveData<Exception>() }

    private val finally by lazy { MutableLiveData<Int>() }

    //运行在UI线程里的协程
    fun launchUI(block:suspend CoroutineScope.()->Unit) = viewModelScope.launch {
        try {
            withTimeout(5000){
                block()
            }
        }catch (e:Exception){
            error.value=e
        }finally {
            finally.value=200
        }
    }

    /**
     * 请求失败，出现异常
     */
    fun  getError() : LiveData<Exception>{
        return  error
    }

    /**
     * 请求完成，在此做一些关闭操作
     */
    fun getFinally():LiveData<Int>{
        return finally
    }

}