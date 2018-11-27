package com.dingwei.dingwei.mvp.model

import com.dingwei.dingwei.mvp.model.bean.LoginBean
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

    /**
     *  添加关注
     */
    fun addAttention(userId:String,otherUserId:String):Observable<BaseResponce<LoginBean>>{
        return RetrofitManager.service.addAttention(userId,otherUserId).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取关注列表
     */
    fun getAttentions(userId:String):Observable<BaseResponce<List<LoginBean>>>{
        return RetrofitManager.service.getAttentions(userId).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 取消关注
     */
    fun cancleAttention(userId:String,otherUserId: String):Observable<BaseResponce<String>>{
        return RetrofitManager.service.cancleAttention(userId,otherUserId).compose(SchedulerUtils.ioToMain())
    }

}