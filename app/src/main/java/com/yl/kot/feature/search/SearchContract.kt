package com.yl.kot.feature.search

import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.HotWord

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

interface SearchContract {
    interface View : IBaseView {
        fun showHotWords(hotWordList: List<HotWord>)
        fun showSearchResult(articleList: List<Article>)
    }

    interface Presenter : IBasePresenter<View> {
        fun getHotWords()
        fun searchArticle(keyword: String?, page: Int)
    }
}