package com.dingwei.dingwei.mvp.model

import com.dingwei.dingwei.mvp.model.bean.LocationPageBean
import com.dingwei.dingwei.net.BaseResponce
import com.dingwei.dingwei.net.RetrofitManager
import com.dingwei.dingwei.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by kangjie on 2018/11/7.
 */
class LocationModel {

    fun addLocation(userId: String,location: String):Observable<BaseResponce<String>>{
        return RetrofitManager.service.addLocation(userId,location).compose(SchedulerUtils.ioToMain())
    }

    fun getLocationByUserId(userId:String,timeDay:String):Observable<List<LocationPageBean>>{
        return RetrofitManager.service.getLocationByUserId(userId,timeDay).compose(SchedulerUtils.ioToMain())
    }
}