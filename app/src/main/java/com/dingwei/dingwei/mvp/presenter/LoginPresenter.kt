package com.dingwei.dingwei.mvp.presenter

import com.dingwei.dingwei.base.BasePresenter
import com.dingwei.dingwei.mvp.contract.LoginContract
import com.dingwei.dingwei.mvp.model.LoginModel

/**
 * Created by kangjie on 2018/10/26.
 */
class LoginPresenter : BasePresenter<LoginContract.View>(),LoginContract.Presenter {
    private val loginModel by lazy {
        LoginModel()
    }
}