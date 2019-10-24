package com.yl.kot.feature.collection

import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.data.entity.Article

/**
 * Author: Want-Sleep
 * Date: 2019/10/24
 * Desc:
 */
class CollectionContract {

    interface View : IBaseView {
        fun onArticleHasCollected(hasCollected: Boolean)

        fun showArticle(articleList: List<Article>)
    }

    interface Presenter : IBasePresenter<View> {
        fun collectOrCancel(article: Article)

        fun queryArticleHasCollected(articleId: Int)

        fun getCollectionArticlesByPage(page: Int)
    }
}