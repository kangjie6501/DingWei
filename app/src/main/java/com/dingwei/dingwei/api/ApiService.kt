package com.dingwei.dingwei.api

import com.dingwei.dingwei.mvp.model.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kangjie on 2018/10/26.
 */
interface ApiService {
    @GET("v1/login")
    fun login(@Query("phone") phone: String,@Query("passWord") passWord: String): Observable<LoginBean>
}