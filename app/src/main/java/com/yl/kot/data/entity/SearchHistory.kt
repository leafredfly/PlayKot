package com.yl.kot.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: Want-Sleep
 * Date: 2019/09/02
 * Desc:
 */

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey
    @ColumnInfo(name = "key_word")
    var keyWord: String,

    @ColumnInfo(name = "search_time")
    var time: Long = System.currentTimeMillis()
)