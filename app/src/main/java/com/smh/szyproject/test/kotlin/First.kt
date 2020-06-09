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
class First : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        L.e("加法:"+sum(1,2).toString())//加法
//        arrays(1,2,3)//可变长函数
//        sumLandba()//Landba表达式
        nullCheck(null)
        parseInt("1")
    }


    private fun nullCheck(str: String?) {//空检查机制
        //类型后面放问好，意思是说可以为空
        var age: String? = str

    }
    fun parseInt(str: String): Int? {
        return 1
    }

    fun sumLandba() {
        val sumLandba: (Int, Int) -> Int = { x, y -> x + y }
        L.e(sumLandba(1, 2).toString())
    }

    fun arrays(vararg vs: Int) {
        for (vv in vs) {
            L.e("循环输出：" + vv)
        }
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

}