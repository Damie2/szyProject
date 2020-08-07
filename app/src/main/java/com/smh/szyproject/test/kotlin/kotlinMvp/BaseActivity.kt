package com.smh.szyproject.test.kotlin.kotlinMvp

import android.app.Activity
import android.os.Bundle

/**
 * author : smh
 * date   : 2020/8/7 10:07
 * desc   :
 */
abstract class BaseActivity : Activity(), IView {
    private val mAllPresenters = HashSet<IPresenter<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        addPresenters()
        mAllPresenters.forEach { it.onCreate(intent) }
    }

    open fun getPresenters():MutableList<IPresenter<*>>{
        return mutableListOf(mPresenter)
    }

    private fun addPresenters() {
        getPresenters().forEach { mAllPresenters.add(it)}
    }

    override fun onStart() {
        super.onStart()
        mAllPresenters.forEach { it.onStart() }
    }

    override fun onResume() {
        super.onResume()
        mAllPresenters.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        mAllPresenters.forEach { it.onPause() }
    }

    override fun onStop() {
        super.onStop()
        mAllPresenters.forEach { it.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAllPresenters.forEach { it.onDestroy() }
    }

    abstract fun getLayoutId():Int

    override fun showProgressDialog() {
        super.showProgressDialog()
    }

    override fun dismissProgressDialog() {
        super.dismissProgressDialog()
    }
}