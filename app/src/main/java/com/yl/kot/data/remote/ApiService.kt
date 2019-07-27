package com.yl.kot.data.remote

import com.yl.kot.data.entity.Banner
import com.yl.kot.data.entity.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
interface ApiService {
    /**
     * 首页banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<List<Banner>>

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<User>

    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     * @param rePassword 密码重复确认
     */
    @FormUrlEncoded
    @POST("user/login")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): Observable<User>

    /**
     * 注销登录
     */
    @POST("user/logout/json")
    fun logout(): Observable<Any>
}