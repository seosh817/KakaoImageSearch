package com.seosh817.kakaoimagesearch.core.data.local.source

import com.seosh817.kakaoimagesearch.core.data.local.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDataSource {

    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    fun getBookmarksByQuery(query: String): Flow<List<BookmarkEntity>>

    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    suspend fun deleteBookmark(imageUrls: List<String>)
}