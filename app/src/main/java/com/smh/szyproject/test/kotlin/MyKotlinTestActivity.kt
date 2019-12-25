package com.smh.szyproject.test.kotlin

import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.widget.TextView
import com.smh.szyproject.R
import com.smh.szyproject.utils.L

class MyKotlinTestActivity :BaseKotlinActivity() {
    private var textTypeface : Typeface?=null

    private var descTypeFace:Typeface?=null

    private var mytest:TextView?=null

    private var alphaAnimation: AlphaAnimation?=null




    override fun initListener() {
    }

    override fun start() {
    }

    override fun initView() {

    }

    override fun initDate() {
       val sumLambda:(Int,Int)->Int={x,y->x+y}

    }

    fun vars(vararg v:Int) {

    }


    override fun layoutId(): Int = R.layout.activity_main
}