package com.yl.kot.feature.search

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.entity.SearchHistory
import com.yl.kot.data.local.DbManager
import com.yl.kot.data.remote.ApiClient
import kotlinx.coroutines.CoroutineExceptionHandler
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

    override fun getSearchHistory() {
        launch(CoroutineExceptionHandler { _, _ ->  }) {
            mView?.showSearchHistory(DbManager.getSearchHistoryDao().getAll())
        }
    }

    override fun clearSearchHistory() {
        launch(CoroutineExceptionHandler { _, _ ->  }) {
            DbManager.getSearchHistoryDao().clearAll()
            mView?.showSearchHistory(ArrayList())
        }
    }

    override fun searchArticle(keyword: String?, page: Int) {
        // Insert search history into db
        launch(CoroutineExceptionHandler { _, _ ->  }) {
            keyword?.let {
                val searchHistory = SearchHistory(it)
                DbManager.getSearchHistoryDao().insertOrReplace(searchHistory)
            }
        }
        launch {
            val articleList = ApiClient.getApiService().searchArticle(keyword, page)
            mView?.showSearchResult(articleList.articleList)
        }
    }
}