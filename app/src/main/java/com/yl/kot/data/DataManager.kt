package com.yl.kot.data

import com.yl.kot.data.entity.ArticleList
import com.yl.kot.data.entity.Banner
import com.yl.kot.data.entity.User
import com.yl.kot.data.remote.ApiClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

object DataManager {
    /**
     * 获取首页Banner
     */
    fun getBanner(): Observable<List<Banner>> {
        return ApiClient.getApiService()
            .getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     */
    fun login(username: String, password: String): Observable<User> {
        return ApiClient.getApiService()
            .login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     * @param rePassword 密码重复确认
     */
    fun register(username: String, password: String, rePassword: String): Observable<User> {
        return ApiClient.getApiService()
            .register(username, password, rePassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 注销登录
     */
    fun logout(): Observable<Any> {
        return ApiClient.getApiService()
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取首页文章列表
     */
    fun getHomeArticle(page: Int): Observable<ArticleList> {
        return ApiClient.getApiService()
            .getHomeArticle(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}