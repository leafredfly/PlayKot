package com.yl.kot.feature.search

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.remote.ApiClient
import kotlinx.coroutines.launch

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class SearchPresenter(view: SearchContract.View) : BasePresenter<SearchContract.View>(view), SearchContract.Presenter {

    override fun getHotWords() {
        launch {
            mView?.showHotWords(ApiClient.getApiService().getHotWords())
        }
    }

    override fun searchArticle(keyword: String?, page: Int) {
        launch {
            val articleList = ApiClient.getApiService().searchArticle(keyword, page)
            mView?.showSearchResult(articleList.articleList)
        }
    }
}