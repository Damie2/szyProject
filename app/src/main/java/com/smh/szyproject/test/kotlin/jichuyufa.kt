package com.smh.szyproject.test.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smh.szyproject.R
import com.smh.szyproject.utils.L

/**
 * author : smh
 * date   : 2020/5/12 14:21
 * desc   : 第一 基础语法
 */
class jichuyufa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        //加法
        add(1, 2)
        Sum(1, 2)
        Sum1(1, 2)
        //可边长参数函数
        vars(1,2,3,4,5)
        //lambad 表达式
        val sumLambda:(Int,Int)->Int = {x,y->x+y}
        L.e(sumLambda(1,5).toString())
        //null检查机制
    }

    private fun add(i: Int, i1: Int): String {
        return (i + i1).toString()
    }

    fun Sum(a: Int, b: Int) = a + b
    //public必须明确写出返回类型
    public fun Sum1(a: Int, b: Int): Int = a + b

    fun vars(vararg v: Int) {
       for(vt in v){
           L.e(vt.toString())
       }
    }

}