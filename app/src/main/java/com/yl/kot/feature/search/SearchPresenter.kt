package com.yl.kot.feature.search

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.DataManager
import com.yl.kot.data.entity.ArticleList
import com.yl.kot.data.entity.HotWord
import com.yl.kot.data.remote.RemoteDataObserver

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class SearchPresenter(view: SearchContract.View) : BasePresenter<SearchContract.View>(view), SearchContract.Presenter {

    override fun getHotWords() {
        DataManager.getHotWords()
            .subscribe(object : RemoteDataObserver<List<HotWord>>(this) {
                override fun onNext(response: List<HotWord>) {
                    mView?.showHotWords(response)
                }
            })
    }

    override fun searchArticle(keyword: String?, page: Int) {
        DataManager.searchArticle(keyword, page)
            .subscribe(object : RemoteDataObserver<ArticleList>(this) {
                override fun onNext(response: ArticleList) {
                    mView?.showSearchResult(response.articleList)
                }
            })
    }
}