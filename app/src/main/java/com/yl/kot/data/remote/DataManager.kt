package com.yl.kot.data.remote

import com.yl.kot.data.entity.Banner
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

object DataManager {
    fun getBanner(): Observable<List<Banner>> {
        return ApiClient.getApiService()
            .getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}