package com.smh.szyproject.test.kotlin

import android.app.Activity
import android.text.TextUtils
import android.widget.Button
import com.smh.szyproject.R
import com.smh.szyproject.other.utils.L


/**
 * @Author smh
 * @Date 2023/3/23 11:42
 */
class Hello {
    //day
    var a = 1 //可变
    val b = 0 //不可变
    var c: Int = 2 //指定具体类型

    fun test() { //无参方法
    }

    fun test1(a: Int, b: String): Int {//有参方法
        return 1
    }

    fun test2(a: Int, b: Int) = a + b //省了return,直接返回a+b

//    if 语句

    fun max(a: Int, b: Int): Int {
        if (a > b)
            return a
        else return b
    }
    // == 相当于equal  ===相当于==

    fun max1(a: Int, b: Int) = if (a > b) a else if (a == b) b else 0

    fun getScore(name: String) = if (name == "a") L.e("a")
    else if (name == "b") "aa"
    else L.e("")

    fun test1(a: Int, b: Int) = if (a > b) "??"
    else if (a < b) "??1"
    else "cc"

    fun test2(name: String) = when (name) { //when必须要else 否则报错
        "1" -> "dd"
        "2" -> "dds"
        else -> "1"
    }

    fun checkNumber(num: String) = when {
        num == "1" -> "1"
        num == "2" -> "2"
        else -> "3"
    }


    fun checkNumber(num: Number) = when (num) {
        is Int -> "1"
        is Double -> "2"
        else -> "3"
    }

    //循环
    fun main() {
        val range = 0..10 //意思有个区间 [0,10]
        for (i in range) {
            if (i == 2) {
                "???输出"
            }
        }

        //如果左闭右开  0打印到9

        for (i in 0 until 10) {
            "打印" + i
        }

    }

    //创建person类
    open class Person() {
        var name = ""
        var age = 0
        fun print() {
            print("")
        }
    }

    fun test2() {
        var person = Person()
        person.age = 20
        person.name = "ss"
        person.print()
    }

    //因为person是final，不可继承，所以需要前面加上open
    //如果没有open，那就相当于 public  final class Person
    //有open，那就是public class Person
    class Studen : Person() {
        var name2 = "2"
        var ag1e = 0
    }

    fun main22() {
        //常规创建
        var list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        //listOf,后续不可添加删除，只能查询
        val list1 = listOf<Int>(1, 2, 3)
//        list1.add(6)错误

        val list2 = mutableListOf<Int>(1, 2, 3)
        list2.add(4) //可添加


        //循环
        for (va in list2) {
            print(va)
        }


//        Map
        val map1 = mapOf<String, String>("1" to "z", "2" to "s", "3" to "e")
//        map1["3"] = "s"//错误

        val map2 = mutableMapOf<String, String>("1" to "z", "2" to "s", "3" to "e")
        map2["3"] = "s"//错误


        //循环
        for ((key, value) in map2) {
            print(key + " ," + value)
        }
    }

    //Lambda应用

    fun lam() {
        //循环list，如果str的长度大于maxStr的长度,那就赋值
        //返回长度最大的元素
        val list = listOf<String>("a", "aa", "dd", "dd")
        var maxStr = ""
        for (str in list) {
            if (str.length > maxStr.length) {
                maxStr = str
            }
        }
        var lamban = { str: String -> str.length }
        var max1Str = list.maxByOrNull(lamban)//返回长度最大的那个


    }

    //线程
    fun thread() {
        Thread {
            println()
        }.start()

        var btn: Button
//        btn.setOnClickListener { println("111") }
    }

    fun t() {
        Thread {
            L.d("")
        }.start()
    }

    //判空 ?.
    fun study(p: Person?) {
        p?.print()//?.代表只有p不为空，才会执行print方法
    }

    //?:是问号前面不为空就返回问号前的值，为空就返回：后面的值
    val ac = null
    val c1 = if (ac != null) "1" else 2
    val c2 = "1" ?: 2

    fun getLength(test: String): Int {
        if (TextUtils.isEmpty(test)) {
            return 1
        } else {
            return 2
        }
    }

    fun getLength1(test: String) = if (TextUtils.isEmpty(test)) 1 else 2

    //第一个问号意思是test可以为空   test后的？.意思是test不能为空，后面的?:意思是如果test.length不为空，就返回它的长度，否则就返回0
    fun getLentgh2(test: String?) = test?.length ?: 0

    //强行编译!!.  ?可以为空
    fun repate(s: Person?) {
        s!!.print()//不管你是不是为空，也直接运行
    }

    //let
    fun letsgo(p: Person) {
        p?.let {
            p.print()
        }
    }

    //内嵌表达式
    fun print1() {
        var name = "halou"
        var nam1e = "halou"
        print("my name is $name. I love $nam1e.")

        print("i my niubi ${if (1 < 2) "??" else "halou"}. ee $nam1e")
    }

    //as类型强转
    val activity  = this as Activity


}


















