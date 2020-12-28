package com.smh.szyproject.test.zkr.dispatchTouchEvent;

/**
 * author : smh
 * date   : 2020/8/19 15:38
 * desc   :事件分发机制
 */
class dispatchTouchEvent {
//    分发的组件包括activity、view和viewGroup
//    Activity包括viewGroup，viewGroup又包括多个view
//    分发的核心  dispatchTouchEvent
//                onTouchEvent
//                onInterceptTouchEvent
//
//    除了viewGroup包含以上这三个方法
//    其他俩activity和onTouchEvent并不包含onInterceptTouchEvent


//如果某个组件返回true，表示已经对事件进行了处理，不用继续再往下分发了
//    如果返回false，表示不处理，接着分发
//
//如果view经过传递或者处理，返回true，activity的分发也返回true，那就被消费了
//    如果子view没被消费，返回false，那activiy就不会返回true，它就调用onTouchEvent，那里面的event怎么处理
//
//    如果onTouchEvent消费了返回true，那这个ture就相当于dispatchTouchEvent的返回值，告诉activity已经被消费
//
//    如果onTouchEvent没消费该事件返回了false，那就接着处理，因为没消费事件
//
//            onInterceptTouchEvent是ViewGroup里的
//            如果返回false，就交给子view的dispatchTouchEvent
//    如果返回了true，就交给viewGroup中的onTouchEvent处理


//    主要方法解释
//    dispatchTouchEvent：事件分发方法
//
//    返回 false：分发事件
//
//    返回 true：不分发事件
//
//    默认返回值：false
//=================================================
//    onInterceptTouchEvent：事件拦截方法
//
//    返回 false：不拦截事件
//
//    返回 true：拦截事件
//
//    默认返回值：false
//=================================================
//    onTouchEvent：事件消费方法
//
//    返回 false：不消费事件
//
//    返回 true：消费事件
//
//    默认返回值：false
//



}
