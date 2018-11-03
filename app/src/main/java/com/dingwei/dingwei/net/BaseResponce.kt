package com.dingwei.dingwei.net

/**
 * Created by kangjie on 2018/11/3.
 */
class BaseResponce<T>(val code :Int,
                      val msg:String,
                      val data:T)