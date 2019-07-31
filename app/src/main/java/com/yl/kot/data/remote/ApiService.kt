package com.yl.kot.data.remote

import com.yl.kot.data.entity.*
import io.reactivex.Observable
import retrofit2.http.*

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
    @POST("/user/login")
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
    @POST("/user/login")
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

    /**
     * 获取首页文章列表
     *
     * @param page page start from 0
     */
    @GET("/article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<ArticleList>

    /**
     * 获取首页置顶文章列表
     *
     */
    @GET("/article/top/json")
    fun getHomeTopArticle(): Observable<MutableList<Article>>

    /**
     * 获取热搜列表
     *
     */
    @GET("/hotkey/json")
    fun getHotWords(): Observable<List<HotWord>>

    /**
     * 搜索文章
     *
     * @param keyword 搜索关键字
     * @param page 页码, 从0开始
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun searchArticle(@Field("k") keyword: String?, @Path("page") page: Int): Observable<ArticleList>
}