package com.seosh817.kakaoimagesearch.core.data.local.source.impl

import android.util.Log
import androidx.paging.PagingSource
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

    override fun getBookmarksByQuery(query: String): PagingSource<Int, BookmarkEntity> {
        return bookmarkDao.getBookmarksByQuery(query)
    }

    override suspend fun insertBookmark(bookmarkEntity: BookmarkEntity) {
        bookmarkDao.insertBookmarkImage(bookmarkEntity)
    }

    override suspend fun deleteBookmark(urls: List<String>) {
        bookmarkDao.deleteBookmarkImage(urls)
    }
}