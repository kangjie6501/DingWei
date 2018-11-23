package com.dingwei.dingwei.mvp.contract

import com.dingwei.dingwei.base.IBaseView
import com.dingwei.dingwei.base.IPresenter
import com.dingwei.dingwei.mvp.model.bean.LocationPageBean

/**
 * Created by kangjie on 2018/11/20.
 */
interface LocationContract {
    interface View:IBaseView{
        fun showLocation(result:List<LocationPageBean>)
        fun showError(errorMsg:Throwable)
    }

    interface Presenter:IPresenter<View>{
        fun getLocationByUserId(userId:String)
    }
}