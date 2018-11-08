package com.dingwei.dingwei.ui.activity

import android.content.Intent
import android.widget.Toast
import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.LoginContract
import com.dingwei.dingwei.mvp.model.bean.LoginBean
import com.dingwei.dingwei.mvp.presenter.LoginPresenter
import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.utils.Preference
import kotlinx.android.synthetic.main.activity_login_layout.*


/**
 * Created by kangjie on 2018/10/24.
 */
class LoginActivity : BaseActivity(),LoginContract.View {

    private var phone :String by Preference("phone","")
    private var name : String by Preference("name","")
    private var password :String by Preference("password","")
    private var id :String by Preference("id","")
    private var token :String by Preference("token","")

    private val mPresenter by lazy { LoginPresenter() }
    override fun layoutId(): Int = R.layout.activity_login_layout
    override fun initView() {
        login_btn.setOnClickListener {
            var phone = login_name_et.text.toString().trim()
            var password = login_password_et.text.toString().trim()
            mPresenter.login(phone,password)
        }

        goto_register_btn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
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
        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
    }

    override fun setLoginView(loginBean: BaseResponce<LoginBean>) {
     //   Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
        saveUserData(loginBean)
        startActivity(Intent(this,MainActivity::class.java))

    }

    private fun saveUserData(loginBean: BaseResponce<LoginBean>) {
        id = loginBean.data.id
        phone = loginBean.data.phone
        name = loginBean.data.name
        password = loginBean.data.password
        token = loginBean.data.token
    }

    init {
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }





}