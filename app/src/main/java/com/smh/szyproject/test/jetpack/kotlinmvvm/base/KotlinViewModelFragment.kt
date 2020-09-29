package com.smh.szyproject.test.jetpack.kotlinmvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

/**
 * author : smh
 * date   : 2020/9/21 10:40
 * desc   :
 */
abstract class KotlinViewModelFragment<VM : KotlinBaseViewModel> : Fragment() {
    private val fragmentName = javaClass.simpleName
    protected lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
        //也就是说在onCrateView先创建view
        //然后再viewCreate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initView()
        initData()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initVM() {//表示object不为null的条件下，才会去执行let函数体
        providerVMClass()?.let {//表示providerVMClass在不为空的情况下，才会执行let里的方法
            viewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(viewModel)

        }
    }

    open fun providerVMClass(): Class<VM>? = null

    open fun startObserve(){}

    /**
     * 必须实现的方法
     */
    abstract fun getLayoutId():Int
    abstract fun initView()
    abstract fun initData()


    override fun onDestroy() {
        super.onDestroy()
        if(this::viewModel.isInitialized)
            lifecycle.removeObserver(viewModel)
    }
}