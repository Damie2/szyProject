package com.smh.szyproject.test.kotlin.kotlinMvp

import android.text.TextUtils

/**
 * author : smh
 * date   : 2020/8/7 10:27
 * desc   :
 */
class UserPresenter(view: UserContract.IUserView) : UserContract.IUserPresenter,
        IPresenter<UserContract.IUserView>(view) {
    override fun addUser(name: String) {
        //弹出dialog
        mView.get()?.showProgressDialog()
        //校验
        if (!TextUtils.isEmpty(name)) {
            UserModel.addUser(name) {
                mView.get()?.dismissProgressDialog()
                mView.get()?.showAddUserResult(it)
            }
        }
    }
}