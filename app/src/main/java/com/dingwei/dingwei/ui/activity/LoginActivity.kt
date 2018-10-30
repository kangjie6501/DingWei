package com.dingwei.dingwei.ui.activity

import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login_layout.*


/**
 * Created by kangjie on 2018/10/24.
 */
class LoginActivity : BaseActivity() {
    private val mPresenter by lazy { LoginPresenter() }
    override fun layoutId(): Int = R.layout.activity_login_layout
    override fun initView() {
        login_btn.setOnClickListener {  }
    }

    override fun start() {
    }

    override fun initData() {
    }




}