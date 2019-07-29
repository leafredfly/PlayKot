package com.yl.kot.feature.home

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.DataManager
import com.yl.kot.data.entity.ArticleList
import com.yl.kot.data.entity.Banner
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
                    mView?.showBanner(t)
                }
            })
    }

    override fun getArticle(page: Int) {
        DataManager.getHomeArticle(page)
            .subscribe(object : RemoteDataObserver<ArticleList>(this) {
                override fun onNext(t: ArticleList) {
                    mView?.showArticle(t.articleList)
                }
            })
    }
}