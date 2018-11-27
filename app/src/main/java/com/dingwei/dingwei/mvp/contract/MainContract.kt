package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter
import com.dingwei.dingwei.mvp.model.bean.LoginBean
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

        /**
         * 添加关注成功
         */
        fun showAddAttentionSuccess(loginBean:LoginBean)
        /**
         * 取消关注成功
         */
        fun showCancleAttentionSuccess(s:String)

        /**
         * 显示关注列表
         */
        fun showAttentions(loginBeans:List<LoginBean>)

    }

    interface Presenter: IPresenter<View> {
        fun clearLocationByUser(userId:String)
        fun addAttention(userId:String,otherUserId: String)
        fun getAttentions(userId:String)
        fun cancleAttention(userId:String,otherUserId:String)
    }
}