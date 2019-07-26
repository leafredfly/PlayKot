package com.yl.kot.data.remote

import com.yl.kot.data.entity.Banner
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
interface ApiService {
    @GET("/banner/json")
    fun getBanner(): Observable<List<Banner>>
}