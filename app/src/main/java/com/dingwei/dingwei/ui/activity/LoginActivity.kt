package com.dingwei.dingwei.ui.activity

import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.LoginContract
import com.dingwei.dingwei.mvp.model.bean.LoginBean
import com.dingwei.dingwei.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login_layout.*


/**
 * Created by kangjie on 2018/10/24.
 */
class LoginActivity : BaseActivity(),LoginContract.View {


    private val mPresenter by lazy { LoginPresenter() }
    override fun layoutId(): Int = R.layout.activity_login_layout
    override fun initView() {
        login_btn.setOnClickListener {
            var phone = login_name_et.text.toString().trim()
            var password = login_password_et.text.toString().trim()
            mPresenter.login(phone,password)
        }
    }

    override fun start() {
    }

    override fun initData() {
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun showError(errorMsg: String) {

    }

    override fun setLoginView(loginBean: LoginBean) {

    }





}