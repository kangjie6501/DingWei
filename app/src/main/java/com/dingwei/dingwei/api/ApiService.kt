package com.dingwei.dingwei.api

import com.dingwei.dingwei.mvp.model.bean.LocationPageBean
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
    fun register(@Field("phone") phone: String, @Field("name")name:String , @Field("password") password: String):
            Observable<BaseResponce<RegisterBean>>

    @FormUrlEncoded
    @POST("addLocation/")
    fun addLocation(@Field("userId") userId: String,@Field("location") location: String): Observable<BaseResponce<String>>

    @FormUrlEncoded
    @POST("clearLocationByUserId/")
    fun clearLocationByUserId(@Field("userId") userId: String): Observable<BaseResponce<String>>

    @FormUrlEncoded
    @POST("getLocationByUserId/")
    fun getLocationByUserId(@Field("userId") userId: String): Observable<List<LocationPageBean>>

    @FormUrlEncoded
    @POST("addAttention/")
    fun addAttention(@Field("userId") userId:String,@Field("otherUserId") otherUserId:String): Observable<BaseResponce<LoginBean>>

    @FormUrlEncoded
    @POST("getAttentions/")
    fun getAttentions(@Field("userId") userId:String): Observable<BaseResponce<List<LoginBean>>>

    @FormUrlEncoded
    @POST("cancleAttention/")
    fun cancleAttention(@Field("userId") userId:String,@Field("otherUserId") otherUserId:String): Observable<BaseResponce<String>>

}