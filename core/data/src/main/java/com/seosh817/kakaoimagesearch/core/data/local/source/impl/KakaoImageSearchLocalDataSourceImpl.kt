package com.seosh817.kakaoimagesearch.core.data.local.source.impl

import com.seosh817.kakaoimagesearch.core.data.local.dao.BookmarkDao
import com.seosh817.kakaoimagesearch.core.data.local.model.BookmarkEntity
import com.seosh817.kakaoimagesearch.core.data.local.source.KakaoImageSearchLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KakaoImageSearchLocalDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : KakaoImageSearchLocalDataSource {

    override fun getAllBookmarks(): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getAllBookmarks()
    }

    override fun getBookmarksByQuery(query: String): Flow<List<BookmarkEntity>> {
        return bookmarkDao.getBookmarksByQuery(query)
    }

    override suspend fun insertBookmarkImage(bookmarkEntity: BookmarkEntity) {
        bookmarkDao.insertBookmarkImage(bookmarkEntity)
    }

    override suspend fun deleteBookmarkImage(ids: List<Int>) {
        bookmarkDao.deleteBookmarkImage(ids)
    }
}