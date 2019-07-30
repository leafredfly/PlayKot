package com.yl.kot.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

data class Article(
        val id: Int,
        val author: String,
        val niceDate: String,
        val link: String,
        val title: String,
        @SerializedName("tags") val tagList: List<ArticleTag>,
        val zan: Int,
        var top: Boolean = false
)