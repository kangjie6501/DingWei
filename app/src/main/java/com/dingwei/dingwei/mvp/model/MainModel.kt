package com.dingwei.dingwei.mvp.model

import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.net.RetrofitManager
import com.dingwei.dingwei.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by kangjie on 2018/11/9.
 */
class MainModel {
    /**
     * 清除位置信息
     */
    fun clearLocationByUserId(userId:String):Observable<BaseResponce<String>>{
        return RetrofitManager.service.clearLocationByUserId(userId).compose(SchedulerUtils.ioToMain())
    }
}