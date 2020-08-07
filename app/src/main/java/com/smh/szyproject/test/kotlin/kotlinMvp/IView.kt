package com.smh.szyproject.test.kotlin.kotlinMvp

import android.content.Context
import android.widget.Toast

/**
 * author : smh
 * date   : 2020/8/7 9:45
 * desc   :
 */
interface IView {
    val  mPresenter:IPresenter<out IView>

    fun initView()

    fun showProgressDialog(){

    }
    fun dismissProgressDialog(){

    }
    fun showToast(text:String,context:Context,time:Int= Toast.LENGTH_SHORT){
        Toast.makeText(context,text,time).show()
    }
}