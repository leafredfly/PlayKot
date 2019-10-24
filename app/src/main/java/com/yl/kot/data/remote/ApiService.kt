package com.yl.kot.data.remote

import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.ArticleList
import com.yl.kot.data.entity.Banner
import com.yl.kot.data.entity.HotWord
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
    suspend fun getBanner(): List<Banner>

    /**
     * 获取首页文章列表
     *
     * @param page page start from 0
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): ArticleList

    /**
     * 获取首页置顶文章列表
     *
     */
    @GET("/article/top/json")
    suspend fun getHomeTopArticle(): MutableList<Article>

    /**
     * 获取热搜列表
     *
     */
    @GET("/hotkey/json")
    suspend fun getHotWords(): List<HotWord>

    /**
     * 搜索文章
     *
     * @param keyword 搜索关键字
     * @param page 页码, 从0开始
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun searchArticle(@Field("k") keyword: String?, @Path("page") page: Int): ArticleList
}