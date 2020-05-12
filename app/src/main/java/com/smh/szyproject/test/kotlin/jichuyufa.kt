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
        var msg = add(1, 2)
        L.e(""+msg)
    }

    private fun add(i: Int, i1: Int): String {
        return (i + i1).toString()
    }
}