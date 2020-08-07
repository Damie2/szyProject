package com.smh.szyproject.test.kotlin.kotlinMvp

/**
 * author : smh
 * date   : 2020/8/7 9:43
 * desc   :
 */
object UserModel{
    fun addUser(name:String,listener:(Boolean)->Unit){
        Thread.sleep(1000)
        if(name=="123"){
            listener(true)
        }else{
            listener(false)
        }
    }
}