package com.smh.szyproject.test.kotlin.kotlinMvp

/**
 * author : smh
 * date   : 2020/8/7 10:26
 * desc   :
 */
interface UserContract {
    interface IUserView:IView{
        fun showAddUserResult(boolean: Boolean)
    }
    interface IUserPresenter{
        fun addUser(name:String)
    }

}