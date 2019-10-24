package com.yl.kot

import com.yl.kot.data.entity.Article

/**
 * Author: Want-Sleep
 * Date: 2019/10/24
 * Desc:
 */
object MemoryData {
    private var mBrowsedArticle: Article? = null

    fun setBrowsedArticle(article: Article) {
        this.mBrowsedArticle = article
    }

    fun getBrowsedArticle() = mBrowsedArticle
}