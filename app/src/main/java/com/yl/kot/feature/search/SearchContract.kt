package com.yl.kot.feature.search

import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.HotWord
import com.yl.kot.data.entity.SearchHistory

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

interface SearchContract {
    interface View : IBaseView {
        fun showHotWords(hotWordList: List<HotWord>)
        fun showSearchHistory(searchHistory: List<SearchHistory>)
        fun showSearchResult(articleList: List<Article>)
    }

    interface Presenter : IBasePresenter<View> {
        fun getHotWords()
        fun getSearchHistory()
        fun clearSearchHistory()
        fun searchArticle(keyword: String?, page: Int)
    }
}