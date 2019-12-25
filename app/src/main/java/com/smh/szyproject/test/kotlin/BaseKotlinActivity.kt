package com.smh.szyproject.test.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//kotlin的baseActivity
abstract class BaseKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initDate()
        initView()
        start()
        initListener()
    }

    abstract fun initListener()

    abstract fun start()

    abstract fun initView()

    abstract fun initDate()

    abstract fun layoutId(): Int


}