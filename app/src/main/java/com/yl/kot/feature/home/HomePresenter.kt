package com.yl.kot.feature.home

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.DataManager
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.ArticleList
import com.yl.kot.data.entity.Banner
import com.yl.kot.data.remote.RemoteDataObserver

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    private var mNormalArticleList: MutableList<Article>? = null
    private var mTopArticleList: MutableList<Article>? = null

    override fun getBanner() {
        DataManager.getBanner()
            .subscribe(object : RemoteDataObserver<List<Banner>>(this) {
                override fun onNext(response: List<Banner>) {
                    mView?.showBanner(response)
                }
            })
    }

    override fun getArticle(page: Int) {
        if (page == 0) {
            getTopArticle()
            mNormalArticleList = null
        }
        DataManager.getHomeArticle(page)
            .subscribe(object : RemoteDataObserver<ArticleList>(this) {
                override fun onNext(response: ArticleList) {
                    if (page == 0) {
                        mNormalArticleList = response.articleList
                        mTopArticleList?.let {
                            it.addAll(response.articleList)
                            mView?.showArticle(it)
                        }
                    } else {
                        mView?.showArticle(response.articleList)
                    }
                }
            })
    }

    override fun getTopArticle() {
        mTopArticleList = null
        DataManager.getHomeTopArticle()
                .subscribe(object : RemoteDataObserver<MutableList<Article>>(this) {
                    override fun onNext(response: MutableList<Article>) {
                        for (article in response) {
                            article.top = true
                        }
                        mTopArticleList = response
                        mNormalArticleList?.let {
                            response.addAll(it)
                            mView?.showArticle(response)
                        }
                    }
                })
    }
}