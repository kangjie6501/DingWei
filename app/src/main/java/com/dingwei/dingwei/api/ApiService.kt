package com.dingwei.dingwei.api

import com.dingwei.dingwei.mvp.model.bean.LoginBean
import com.dingwei.dingwei.mvp.model.bean.RegisterBean
import com.dingwei.dingwei.net.BaseResponce
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by kangjie on 2018/10/26.
 */
interface ApiService {
    @GET("login/")
    fun login(@Query("phone") phone: String,@Query("password") password: String): Observable<BaseResponce<LoginBean>>

    @FormUrlEncoded
    @POST("register/")
  //  @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    fun register(@Field("phone") phone: String,@Field("password") password: String): Observable<BaseResponce<RegisterBean>>

    @FormUrlEncoded
    @POST("addLocation/")
 //   @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    fun addLocation(@Field("userId") userId: String,@Field("location") location: String): Observable<BaseResponce<String>>


}