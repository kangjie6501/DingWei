package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter
import com.dingwei.dingwei.mvp.model.bean.LoginBean
import com.dingwei.dingwei.net.BaseResponce

/**
 * Created by kangjie on 2018/10/26.
 */
interface LoginContract {
    interface View: IBaseView {
        /**
         * 设置登录提示
         */
        fun setLoginView(loginBean:BaseResponce<LoginBean>)

        /**
         * 错误信息
         */
        fun showError(errorMsg:String)

    }

    interface Presenter: IPresenter<View> {
        fun login(phone: String,passWord: String)
    }
}