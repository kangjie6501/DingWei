package com.dingwei.dingwei.mvp.model.bean

/**
 * Created by kangjie on 2018/11/23.
 */
data class LocationPageBean(var model:String, var pk: Int,var fields:Fields){
    data class Fields(var jing:String,var person:Int,var time:String,var wei:String)
}
