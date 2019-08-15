package com.yl.kot.feature.home

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.entity.Article
import com.yl.kot.data.remote.ApiClient
import kotlinx.coroutines.launch

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    private var mNormalArticleList: MutableList<Article>? = null
    private var mTopArticleList: MutableList<Article>? = null

    override fun getBanner() {
        launch {
            mView?.showBanner(ApiClient.getApiService().getBanner())
        }
    }

    override fun getArticle(page: Int) {
        if (page == 0) {
            getTopArticle()
            mNormalArticleList = null
        }
        launch {
            val articleList = ApiClient.getApiService().getHomeArticle(page)
            if (page == 0) {
                mNormalArticleList = articleList.articleList
                mTopArticleList?.let {
                    it.addAll(articleList.articleList)
                    mView?.showArticle(it)
                }
            } else {
                mView?.showArticle(articleList.articleList)
            }
        }
    }

    override fun getTopArticle() {
        mTopArticleList = null
        launch {
            val topArticleList = ApiClient.getApiService().getHomeTopArticle()
            for (article in topArticleList) {
                article.top = true
            }
            mTopArticleList = topArticleList
            mNormalArticleList?.let {
                topArticleList.addAll(it)
                mView?.showArticle(topArticleList)
            }
        }
    }
}