package com.smh.szyproject.test.jetpack

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.arraySetOf
import com.smh.szyproject.other.utils.L


/**
 * author : smh
 * date   : 2020/9/17 10:41
 * desc   :
 */
class KTX : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {//加问号代表可以为空
        super.onCreate(savedInstanceState)
        setMethod()
    }

    fun setMethod() {
        var set1=LinkedHashSet<Int>()
        set1.add(1)
        set1.add(2)
        set1.add(3)
        var set2=LinkedHashSet<Int>()
        set2.add(4)
        set2.add(5)
        set2.add(6)

        // 上面是普通kotlin，下面是KTX的一个语法糖
        var set3 = arraySetOf(1,2,3)+ arraySetOf(4,5,6)
        L.e(set3.toString());
    }



}