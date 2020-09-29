package com.smh.szyproject.test.jetpack.kotlinmvvm.databean

/**
 * author : smh
 * date   : 2020/9/18 14:25
 * desc   :
 */
data class Data(
        val children:List<Any>,
        val courseId  :Int ,
        val id:Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
)

