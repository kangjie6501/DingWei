package com.dingwei.dingwei.mvp.model

import com.dingwei.dingwei.mvp.model.bean.RegisterBean
import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.net.RetrofitManager
import com.dingwei.dingwei.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by kangjie on 2018/11/2.
 */
class RegisterModel {
    /**
     * 注册
     */
    fun register(phone: String,name:String,password: String): Observable<BaseResponce<RegisterBean>>{
        return RetrofitManager.service.register(phone,name,password).compose(SchedulerUtils.ioToMain())
    }
}