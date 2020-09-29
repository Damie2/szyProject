package com.smh.szyproject.test.jetpack.kotlinmvvm.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.lang.Exception
import kotlinx.coroutines.TimeoutCancellationException as TimeoutCancellationException1

/**
 * author : smh
 * date   : 2020/9/18 16:18
 * desc   :
 */
abstract class KotlinBaseViewModelActivity<VM : KotlinBaseViewModel> : KotlinBaseActivity() {

    //lateinit s是延迟加载
    //lateinit 只用于 var，而 lazy 只用于 val
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()
    }

    private fun startObserve() {
        //处理一些通用异常，比如网络超时
        viewModel.run {
            getError().observe(this@KotlinBaseViewModelActivity, Observer {
                requestError(it)
            })
            getFinally().observe(this@KotlinBaseViewModelActivity, Observer {
                requestFinally(it)
            })
        }
    }

    open fun requestFinally(it: Int?) {

    }

    open fun requestError(it: Exception?) {
        //处理一些已知异常
        it?.run {
            when (it) {
                is TimeoutCancellationException1 -> {
                    showToast("请求超时")
                }
                is KotlinBaseRepository.TokenInvalidException -> {
                    showToast("登陆超时")
                }
            }
        }
    }


    private fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(viewModel)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }
}