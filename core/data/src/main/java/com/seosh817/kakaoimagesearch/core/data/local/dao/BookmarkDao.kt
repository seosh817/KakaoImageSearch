package com.seosh817.kakaoimagesearch.core.data.local.dao

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
            WHERE :query LIKE '%' || :query || '%'
        """
    )
    fun getBookmarksByQuery(query: String): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkImage(bookmarkEntity: BookmarkEntity)

    @Query(
        value =
        """
            DELETE
            FROM bookmark_table
            WHERE id IN (:ids)
        """
    )
    suspend fun deleteBookmarkImage(ids: List<Int>)
}