package com.seosh817.kakaoimagesearch.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seosh817.kakaoimagesearch.core.data.local.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query(
        value =
        """
            SELECT * 
            FROM bookmark_table
        """
    )
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query(
        value =
        """
            SELECT *
            FROM bookmark_table
            WHERE CASE
                WHEN :query IS NULL OR :query = '' THEN 1
                ELSE `query` LIKE '%' || :query || '%'
            END
        """
    )
    fun getBookmarksByQuery(query: String): PagingSource<Int, BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkImage(bookmarkEntity: BookmarkEntity)

    @Query(
        value =
        """
            DELETE
            FROM bookmark_table
            WHERE image_url IN (:imageUrls)
        """
    )
    suspend fun deleteBookmarkImage(imageUrls: List<String>)
}