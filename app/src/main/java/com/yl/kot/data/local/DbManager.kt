package com.yl.kot.data.local

import androidx.room.Room
import com.yl.kot.App

/**
 * Author: Want-Sleep
 * Date: 2019/09/03
 * Desc:
 */

class DbManager private constructor() {

    companion object {
        private val INSTANCE: DbManager by lazy {
            DbManager()
        }

        fun getSearchHistoryDao(): SearchHistoryDao = INSTANCE.mAppDb.searchHistoryDao()
    }

    private val mAppDb: AppDatabase = Room.databaseBuilder(
        App.getInstance().applicationContext,
        AppDatabase::class.java, "kot.db"
    ).build()

}