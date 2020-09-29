package com.smh.szyproject.test.jetpack.kotlinmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smh.szyproject.test.jetpack.kotlinmvvm.base.KotlinBaseViewModel
import com.smh.szyproject.test.jetpack.kotlinmvvm.databean.Data
import com.smh.szyproject.test.jetpack.kotlinmvvm.repository.ArticleRepository

/**
 * author : smh
 * date   : 2020/9/22 10:50
 * desc   :
 */
class ScrollingViewModel :KotlinBaseViewModel(){
    private val TAG = ScrollingViewModel::class.java.simpleName

    private val datas: MutableLiveData<List<Data>> by lazy { MutableLiveData<List<Data>>().also { loadDatas() } }

    private val repository = ArticleRepository()

    fun getActicle(): LiveData<List<Data>> {
        return datas
    }

    private fun loadDatas() = launchUI {
        val result = repository.getDatas()
        datas.value = result.data
    }
}

