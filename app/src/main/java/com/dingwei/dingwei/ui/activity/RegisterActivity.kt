package com.dingwei.dingwei.ui.activity

import android.content.Intent
import android.widget.Toast
import com.dingwei.dingwei.R
import com.dingwei.dingwei.base.BaseActivity
import com.dingwei.dingwei.mvp.contract.RegisterContract
import com.dingwei.dingwei.mvp.model.bean.RegisterBean
import com.dingwei.dingwei.mvp.presenter.RegisterPersenter
import com.dingwei.dingwei.net.BaseResponce
import kotlinx.android.synthetic.main.activity_register_layout.*


/**
 * Created by kangjie on 2018/11/2.
 */
class RegisterActivity :BaseActivity(), RegisterContract.View {

    override fun layoutId(): Int =  R.layout.activity_register_layout
    private val mPresenter by lazy {
        RegisterPersenter()
    }

    init {
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
    override fun initView() {
        register_btn.setOnClickListener {
            var phone = register_phone_et.text.toString().trim()
            var password = register_password_et.text.toString().trim()
            var name = register_name_et.text.toString().trim()
            mPresenter.register(phone,name,password)
        }

        goto_login_btn.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }



    override fun showLoading() {

    }

    override fun dismissLoading() {
           }

    override fun setRegisterView(register: BaseResponce<RegisterBean>) {
        Toast.makeText(this,"succes"+ register.data.phone+"  " +register.data.password, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(this,errorMsg, Toast.LENGTH_SHORT).show()
    }





    override fun initData() {

    }


    override fun start() {

    }

}