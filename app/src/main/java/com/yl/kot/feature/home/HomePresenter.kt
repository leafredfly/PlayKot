package com.yl.kot.feature.home

import android.util.Log
import com.yl.kot.base.BasePresenter
import com.yl.kot.data.entity.Banner
import com.yl.kot.data.remote.DataManager
import com.yl.kot.data.remote.RemoteDataObserver

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    override fun getBanner() {
        DataManager.getBanner()
            .subscribe(object : RemoteDataObserver<List<Banner>>(this) {
                override fun onNext(t: List<Banner>) {
                    Log.d("Debug", "" + t.size)
                }
            })
    }
}