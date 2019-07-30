package com.yl.kot.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

data class ArticleList(
    val curPage: Int,
    @SerializedName("datas") val articleList: MutableList<Article>,
    val pageCount: Int,
    val size: Int,
    val total: Int
)