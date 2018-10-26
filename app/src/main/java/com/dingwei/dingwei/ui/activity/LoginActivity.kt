package com.dingwei.dingwei.ui.activity

import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.presenter.LoginPresenter

/**
 * Created by kangjie on 2018/10/24.
 */
class LoginActivity : BaseActivity() {
    private val mPresenter by lazy { LoginPresenter() }
    override fun layoutId(): Int = R.layout.activity_login_layout
    override fun initView() {

    }

    override fun start() {

    }

    override fun initData() {

    }




}