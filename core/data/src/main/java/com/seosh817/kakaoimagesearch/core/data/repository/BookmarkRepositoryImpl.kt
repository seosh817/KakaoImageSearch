package com.seosh817.kakaoimagesearch.core.data.repository

import com.seosh817.kakaoimagesearch.core.data.local.source.BookmarkLocalDataSource
import com.seosh817.kakaoimagesearch.core.data.mapper.asDomainEntity
import com.seosh817.kakaoimagesearch.core.data.mapper.asLocalEntity
import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkLocalDataSource: BookmarkLocalDataSource
) : BookmarkRepository {

    override fun getAllBookmarks(): Flow<List<Bookmark>> {
        return bookmarkLocalDataSource
            .getAllBookmarks()
            .map {
                it.map { bookmarkEntity ->
                    bookmarkEntity.asDomainEntity()
                }
            }
    }

    override fun getBookmarksByQuery(query: String): Flow<List<Bookmark>> {
        return bookmarkLocalDataSource
            .getBookmarksByQuery(query)
            .map {
                it.map { bookmarkEntity ->
                    bookmarkEntity.asDomainEntity()
                }
            }
    }

    override suspend fun insertBookmark(bookmark: Bookmark) {
        return bookmarkLocalDataSource.insertBookmark(bookmark.asLocalEntity())
    }

    override suspend fun deleteBookmark(urls: List<String>) {
        return bookmarkLocalDataSource.deleteBookmark(urls)
    }
}
