package com.yl.kot.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yl.kot.data.entity.SearchHistory

/**
 * Author: Want-Sleep
 * Date: 2019/09/03
 * Desc:
 */
@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history order by search_time desc")
    suspend fun getAll(): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(searchHistory: SearchHistory)

    @Query("DELETE FROM search_history")
    suspend fun clearAll()
}