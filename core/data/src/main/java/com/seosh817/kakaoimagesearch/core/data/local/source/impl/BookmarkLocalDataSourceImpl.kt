package com.seosh817.kakaoimagesearch.core.data.local.source.impl

import android.util.Log
import com.seosh817.kakaoimagesearch.core.data.local.dao.BookmarkDao
import com.seosh817.kakaoimagesearch.core.data.local.model.BookmarkEntity
import com.seosh817.kakaoimagesearch.core.data.local.source.BookmarkLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkLocalDataSource {

    override fun getAllBookmarks(): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getAllBookmarks()
    }

    override fun getBookmarksByQuery(query: String): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getBookmarksByQuery(query)
    }

    override suspend fun insertBookmark(bookmarkEntity: BookmarkEntity) {
        bookmarkDao.deleteBookmarkImage(urls)
    }
}