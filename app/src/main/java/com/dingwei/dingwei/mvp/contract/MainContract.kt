package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter
import com.dingwei.dingwei.net.BaseResponce

/**
 * Created by kangjie on 2018/11/9.
 */
interface MainContract {
    interface View: IBaseView {
        /**
         * 清除位置信息
         */
        fun setClearLocationView(loginBean: BaseResponce<String>)

        /**
         * 错误信息
         */
        fun showError(errorMsg:String)

    }

    interface Presenter: IPresenter<View> {
        fun clearLocationByUser(userId:String)
    }
}