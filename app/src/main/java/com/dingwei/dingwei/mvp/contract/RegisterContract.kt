package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter
import com.dingwei.dingwei.mvp.model.bean.RegisterBean
import com.dingwei.dingwei.net.BaseResponce

/**
 * Created by kangjie on 2018/11/2.
 */
interface RegisterContract {
    interface View: IBaseView{
        fun setRegisterView(register: BaseResponce<RegisterBean>)
        fun showError(errorMsg:String)
    }

    interface Presenter: IPresenter<View>{
        fun register(phone: String,password: String)
    }
}