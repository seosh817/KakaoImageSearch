package com.seosh817.kakaoimagesearch.domain.repository

import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    fun getAllBookmarks(): Flow<List<Bookmark>>

    fun getBookmarksByQuery(query: String): Flow<List<Bookmark>>

    suspend fun insertBookmarkImage(bookmarkEntity: Bookmark)

    suspend fun deleteBookmarkImage(ids: List<Int>)
}