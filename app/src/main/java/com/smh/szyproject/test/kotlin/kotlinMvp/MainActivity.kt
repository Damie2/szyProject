package com.smh.szyproject.test.kotlin.kotlinMvp

import android.widget.TextView
import com.smh.szyproject.R

/**
 * author : smh
 * date   : 2020/8/7 10:30
 * desc   :
 */
class MainActivity : BaseActivity(), UserContract.IUserView {

    val tv_next = findViewById(R.id.tv_next) as TextView
    override val mPresenter: UserPresenter = UserPresenter(this)

    override fun getLayoutId(): Int = R.layout.test_activity_test

    override fun initView() {

        tv_next.setOnClickListener {
            mPresenter.addUser("卡丽熙")
        }
    }


    override fun showAddUserResult(boolean: Boolean) {
        if (boolean) {
            tv_next.text = "添加用户成功"
        } else {
            tv_next.text = "添加用户失败"

        }
    }
}