package com.smh.szyproject.test.kotlin.weituo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smh.szyproject.R
import com.smh.szyproject.other.utils.L

/**
 * author : smh
 * date   : 2020/7/3 14:45
 * desc   : kotlin的委托  by
 */
class weituo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //类委托，就是把一个类交给另外一个类，通过by沟通，然后执行方法

        val b  = BaseImpl(10)
        Derived(b).print()
    }

    //==================属性委托===============
//        class Example{
//        var p:String by Delegate()
//    }
//
//    class Delegate{
//        operator
//    }



    //==================属性委托===============

    //==================类委托===============
    //创建接口
    interface Base {
        fun print();
    }

    //实现被委托的类
    class BaseImpl(val x: Int) : Base {
        override fun print() {
            L.e("" + x);
        }
    }

    //通过关键字by建立委托类
    class Derived(b:Base):Base by b{
    }
//==================类委托===============


}