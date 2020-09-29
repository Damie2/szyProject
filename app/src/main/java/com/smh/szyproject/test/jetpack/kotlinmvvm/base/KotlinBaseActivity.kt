package com.smh.szyproject.test.jetpack.kotlinmvvm.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smh.szyproject.other.utils.ToastUtils

/**
 * author : smh
 * date   : 2020/9/18 14:34
 * desc   :
 */
abstract class KotlinBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun getLayoutId(): Int
    abstract fun initData();

    protected fun startActivity(z: Class<*>) {
        startActivity(Intent(applicationContext, z))
    }
    protected  fun showToast(text:String){
        ToastUtils.showToastForText(applicationContext,text)
    }
}