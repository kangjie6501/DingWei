package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter
import com.dingwei.dingwei.mvp.model.bean.LocationBean
import com.dingwei.dingwei.net.BaseResponce

/**
 * Created by kangjie on 2018/11/20.
 */
interface LocationContract {
    interface View:IBaseView{
        fun showLocation(result:BaseResponce<LocationBean>)
        fun showError(errorMsg:Throwable)
    }

    interface Presenter:IPresenter<View>{
        fun getLocationByUserId(userId:String)
    }
}