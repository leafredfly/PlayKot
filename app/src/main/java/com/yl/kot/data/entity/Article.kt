package com.yl.kot.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */
@Entity(tableName = "collection_article")
data class Article(
    @PrimaryKey var id: Int = 0,
    var author: String = "",
    @ColumnInfo(name = "share_user") var shareUser: String = "",
    @Ignore var niceDate: String = "",
    var link: String = "",
    var title: String = "",
    @Ignore @SerializedName("tags") var tagList: List<ArticleTag> = LinkedList(),
    var zan: Int = 0,
    var top: Boolean = false,
    @ColumnInfo(name = "collection_time") var collectionTime: Long = 0L
) {
    fun getFormatCollectionTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(collectionTime))
    }
}