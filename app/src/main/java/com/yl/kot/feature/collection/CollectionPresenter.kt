package com.yl.kot.feature.collection

import com.yl.kot.base.BasePresenter
import com.yl.kot.data.entity.Article
import com.yl.kot.data.local.DbManager
import kotlinx.coroutines.launch

/**
 * Author: Want-Sleep
 * Date: 2019/10/24
 * Desc:
 */
class CollectionPresenter(view: CollectionContract.View) : BasePresenter<CollectionContract.View>(view), CollectionContract.Presenter {

    override fun collectOrCancel(article: Article) {
        launch {
            val collectedArticle = DbManager.getCollectionArticleDao()
                .getCollectionArticleById(article.id)

            if (collectedArticle !== null) {
                DbManager.getCollectionArticleDao().removeCollectionArticle(article)
                mView?.onArticleHasCollected(false)
            } else {
                article.collectionTime = System.currentTimeMillis()
                DbManager.getCollectionArticleDao().insertOrReplace(article)
                mView?.onArticleHasCollected(true)
            }
        }
    }

    override fun queryArticleHasCollected(articleId: Int) {
        launch {
            val article = DbManager.getCollectionArticleDao()
                .getCollectionArticleById(articleId)
            if (article !== null) {
                mView?.onArticleHasCollected(true)
            } else {
                mView?.onArticleHasCollected(false)
            }
        }
    }

    override fun getCollectionArticlesByPage(page: Int) {
        launch {
            mView?.showArticle(DbManager.getCollectionArticleDao().getCollectionArticlesByPage(page))
        }
    }
}