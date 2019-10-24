package com.yl.kot.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.SearchHistory

/**
 * Author: Want-Sleep
 * Date: 2019/09/03
 * Desc:
 */

@Database(entities = [SearchHistory::class, Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao

    abstract fun collectionArticleDao(): CollectionArticleDao
}