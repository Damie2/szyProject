package com.smh.szyproject.test.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.smh.szyproject.R
import com.smh.szyproject.other.utils.L
import kotlinx.coroutines.*
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

        } catch (e: Exception) {
            L.e("" + e.printStackTrace())

        }

    }

    private fun ExceptionsTwo() {
        val number = try {
            Integer.parseInt("1")
        } catch (e: Exception) {
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
        var j: (Int, Int) -> Int = { x, y -> x + y }
        var result3 = j(3, 5)
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
        var nub2 = numble1?.toInt()


        //两个叹号意思是确保输入进来的数据一定不为空
        var nub3 = numble1!!.toInt()
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
        var method = "ssss"
        //val是只读属性，不可改变
        val contury = "china"
        //指定数据类型
        var i: Int = 123
        var j: Long = 999999999999
        var s: String = "111"
    }

    fun mod(b: Int, c: String) {
        var a = 1
        var b = "2"

    }

    /**
     * 协程
     *
     * 轮子的简书
     * https://www.jianshu.com/p/2e0746c7d4f3
     *
     *
     * 关键字suspend 代表当前线程是耗时线程，类似handler，挂起当前线程，且当前线程为非阻塞线程
     *
     * 加个问号代表参数可以为空，不加问好则是会报错
     *
     * 常见的操作符
     * runBlocking 意思是运行阻塞 ，会阻塞当前线程
     *
     * launch 意思是启动，不会阻塞当前线程，但是会执行异步代码
     *
     * async 意思是异步，跟launch差不多，但是它可以有返回值
     * */
    suspend fun getUserName(userId: Int): String {

//        集成协程
//        dependencies {
//            // Kotlin 协程：https://github.com/Kotlin/kotlinx.coroutines
//            implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
//            implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
//        }
        delay(2000)


        return "???"
    }

    suspend fun getBlocking(userId: Int): String {
        /**
         * 协程 runBlocking
         */
        runBlocking {
            //运行在主线程,它会阻塞两秒
            delay(2000)
        }
        return "???"
    }


    /**
     * launch 协程 用的比较多
     * 不会阻塞当前线程，但是会执行异步代码
     * 这种效果类似 new Thread()
     */
    suspend fun getLaunch(userId: Int): String {
        GlobalScope.launch {
            delay(1000)
        }
        return "???"
    }

    /**
     * async
     *async 可以有返回值
     */
    suspend fun getasync(userId: Int): String {
        GlobalScope.async {
            delay(20000)
        }
        return "???"
    }

    /**
     * 协程的线程调度器
     * Dispatchers.Main：主线程调度器，人如其名，会在主线程中执行
     * Dispatchers.IO：工作线程调度器，人如其名，会在子线程中执行
     * Dispatchers.Default：默认调度器，没有设置调度器时就用这个，经过测试效果基本等同于 Dispatchers.IO
     * Dispatchers.Unconfined：无指定调度器，根据当前执行的环境而定，会在当前的线程上执行，另外有一点需要注意，由于是直接拿当前线程执行，经过实践，协程块中的代码执行过程中不会有延迟，会被立马执行，除非遇到需要协程被挂起了，才会去执行协程外的代码，这个也是跟其他类型的调度器不相同的地方
     */

    suspend fun getduq(userId: Int): String {
        GlobalScope.launch(Dispatchers.Main) {
            delay(20000)
        }
        GlobalScope.launch(Dispatchers.IO) {
            delay(20000)
        }
        GlobalScope.launch(Dispatchers.Default) {
            delay(20000)
        }
        GlobalScope.launch(Dispatchers.Unconfined) {
            delay(20000)
        }
        return "???"
    }

    /**
     * withContext 作用就是将当前线程挂起，只有当 withContext 里面的代码执行完了，才会恢复当前线程的执行
     */
    suspend fun name(a: Int): String {
        return withContext(Dispatchers.IO) {
            delay(2000)
            return@withContext "???"
        }
    }

    /**
     * 协程的启动模式
     * CoroutineStart.DEFAULT：默认模式，会立即执行
     * CoroutineStart.LAZY：懒加载模式，不会执行，只有手动调用协程的 start 方法才会执行
     * CoroutineStart.ATOMIC：原子模式，跟 CoroutineStart.DEFAULT 类似，但协程在开始执行之前不能被取消，需要注意的是，这是一个实验性的 api，后面可能会发生变更。
     * CoroutineStart.UNDISPATCHED：未指定模式，会立即执行协程，经过实践得出，会导致原先设置的线程调度器失效，一开始会在原来的线程上执行，类似于 Dispatchers.Unconfined，但是一旦协程被挂起，再恢复执行，会变成线程调度器的设置的线程上面去执行。
     *
     */
    val job = GlobalScope.launch(Dispatchers.Default, CoroutineStart.LAZY) {
        /**
         * job.start：启动协程，除了 lazy 模式，协程都不需要手动启动
         *job.cancel：取消一个协程，可以取消，但是不会立马生效，存在一定延迟
         *job.join：等待协程执行完毕，这是一个耗时操作，需要在协程中使用
         *job.cancelAndJoin：等待协程执行完毕然后再取消
         */

    }.start()

    /**
     * 协程设置执行超时
     */
    suspend fun name1(a: Int) {
        GlobalScope.launch() {
            try {
                withTimeout(300) {
                    //重复执行5次里面的内容
                    repeat(5) { i ->
                        print("aaa")
                        delay(100)
                    }
                }
            } catch (e: TimeoutCancellationException) {
                // TimeoutCancellationException 是 CancellationException 的子类
                println("测试协程超时了")
            }

        }
    }

    /**
     * 协程的生命周期控制
     * 通过CoroutineScope开启的协程是全局的生命周期，不会随Activity等的生命周期，
     *
     * 那么为了解决这一问题，Jetpack 中其实有提供关于 Kotlin 协程的一些扩展组件，例如 LifecycleScope 和 ViewModelScope，集成的方式如下
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'

     */
    fun test() {
        //如果是在 LifecycleOwner 的子类（AppCompatActivity 和 Fragment 都是它的子类）中使用，这样写出来的协程会在 Lifecycle 派发 destroy 事件的时候 cancel 掉
        lifecycleScope.launch {

        }
    }

    fun test1() {
//        如果是在 ViewModel 的子类中使用，这样写出来的协程会在 ViewModel 调用 clear 方法的时候 cancel 掉
//        viewModelScope.launch() {
//
//        }
    }

    //如果我是在 Lifecycle 或者 ViewModel 之外的地方使用协程，又担心内存泄漏，那么该怎么办呢？
    fun test2() {
        val launch = GlobalScope.launch() { }
        launch.cancel()
    }
}



















