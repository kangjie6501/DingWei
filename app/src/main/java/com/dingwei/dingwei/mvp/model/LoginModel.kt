package com.dingwei.dingwei.mvp.model

import com.dingwei.dingwei.mvp.model.bean.LoginBean
import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.net.RetrofitManager
import com.dingwei.dingwei.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by kangjie on 2018/10/26.
 */
class LoginModel {
    /**
     * 登录
     */
    fun login(phone: String,password: String): Observable<BaseResponce<LoginBean>> {
        return RetrofitManager.service.login(phone,password).compose(SchedulerUtils.ioToMain())
    }
}