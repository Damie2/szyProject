package com.smh.szyproject.test.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smh.szyproject.R
import com.smh.szyproject.other.utils.L
import java.lang.Exception

/**
 * author : smh
 * date   : 2020/4/22 16:18
 * desc   :
 */
class HelloWord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_test)
        varDecal()
        BooleanTest()
        createFun()
        //空值处理
        nullDemo(null)
        //when
        whenFun(10)
        //循环和区间
        loopAndRange()
        //带返回值的when
        whenFun1(5)
        //list和map
        ListAndMap()
        //函数和函数表达式
        funDemos()
        //字符串 数字转换
        change()
        //异常处理
        Exceptions()
        ExceptionsTwo()
        //递归
        recursion()
    }



    private fun recursion() {
        var num = 5
        //计算5的阶乘

    }

    private fun Exceptions() {
        try {

        }catch (e:Exception){
            L.e(""+e.printStackTrace())

        }

    }

    private fun ExceptionsTwo() {
        val number = try {
            Integer.parseInt("1")
        }catch (e:Exception){
            return
        }
        L.e("")
    }

   /** 一款小额贷款类的APP，项目主要涉及贷款、还款等流程。 其中包括身份认证OCR、人脸识别、银行卡绑定、获取运营商报表认证等逻辑
    * 该项目使用jsBridge等参与网页的交互
    *
*/
    //字符串 数字转换
     fun change() {
        var a = "123"
        var b = 123
        a = b.toString()
        b = a.toInt()
        //他们会自动to转换
    }


    private fun funDemos() {
        add(1, 2)  //这是个add方法
        //这个delete是一个方法，等同于上面的add
        var i = { x: Int, y: Int -> x - y }
        //我想调用i方法，就直接塞值
        var res = i(5, 3)
        L.e("" + res)

        //别的写法
        var j:(Int,Int)->Int={x,y->x+y}
        var result3=j(3,5)
    }

    fun add(x: Int, y: Int): Int = x + y
    //等同于下面
//    fun add1(i: Int, i1: Int): Int {
//        return i + i1
//    }

    //list和map
    fun ListAndMap() {
        var lists = listOf("1", "2", "3", "4")//声明一个list，里面加进去数
        for (n in lists) {
            L.e(n)
        }

        var map = HashMap<String, String>()
        map["好"] = "good"
        map["学习"] = "study"
        //key和value对应
        L.e(map["好"])//这样就能打印出good了
    }

    fun loopAndRange() {//kotlin的循环和区间
        var nums = 1..100 //这句话的意思是我声明了一个数组，数组里面是1、2、3、4、5  里面有100个
        for (num in nums) { //用in关键字
            L.e("" + num)
        }
        //结果是5050
        //1..100是闭区间 [1到100]

        //下面是开区间  这是前闭后开区间
        var n = 1 until 100    //[1,100}
        for (num in n) { //循环是输出从1到99，不包含100
            //这是前闭后开区间
        }

        var nums2 = 1..16
        for (a in nums2 step 2) {
            //输出1 3 5 7 9 11 13 15
        }

    }

    //when表达式
    fun whenFun(score: Int) {//类似java的switch
        when (score) {
            10 -> L.e("10分")
            9 -> L.e("9分")
            8 -> L.e("8分")
            7 -> L.e("7分")
            6 -> L.e("6分")
            else -> ("其他")
        }
    }

    fun whenFun1(num: Int): String {
        var result = when (num) {
            1 -> "1"
            2 -> "2"
            3 -> "3"
            4 -> "4"
            5 -> "5"
            else -> "eeeee"
        }
        return result
    }

    //加个问号代表参数可以为空，不加问好则是会报错
    fun nullDemo(str: String?) {
//        var numble = readLine()
        var numble1 = readLine()
//        var nub1  = numble.toInt()
        //如果加上问好，就说明如果是空值，就不做转换
        var nub2  = numble1?.toInt()


        //两个叹号意思是确保输入进来的数据一定不为空
        var nub3  = numble1!!.toInt()
    }

    private fun createFun() {
        //创建函数的方法
        plus(1, 2)
    }

    /**
     * 接收俩参数 两个int类型的a和b，它的返回值也是int
     */
    fun plus(a: Int, b: Int): Int {
        return a + b
    }

    fun plus(): Int {
        return 1
    }

    fun plus2(a: Int, b: Int) {
        if (a < 0) {
        }
    }

    fun plus1() {
    }

    fun BooleanTest() {
        var num1 = 1
        var num2 = 2
        if (num1 < num2) {
            L.e("???")
        }
    }

    //变量的声明和使用
    fun varDecal() {
        //var是变量的意思
        var name = "张三"
        var method ="ssss"
        //val是只读属性，不可改变
        val contury = "china"
        //指定数据类型
        var i: Int = 123
        var j: Long = 999999999999
        var s: String = "111"
    }
}