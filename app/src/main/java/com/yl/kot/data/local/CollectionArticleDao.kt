package com.yl.kot.data.local

import androidx.room.*
import com.yl.kot.data.entity.Article

/**
 * Author: Want-Sleep
 * Date: 2019/10/23
 * Desc:
 */
@Dao
interface CollectionArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(article: Article)

    @Query("SELECT * FROM collection_article order by collection_time DESC LIMIT 10 OFFSET :page")
    suspend fun getCollectionArticlesByPage(page: Int): List<Article>

    @Query("SELECT * FROM collection_article where id = :id")
    suspend fun getCollectionArticleById(id: Int): Article?

    @Delete
    suspend fun removeCollectionArticle(article: Article)
}