package com.seosh817.kakaoimagesearch.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.seosh817.kakaoimagesearch.core.common.Dispatcher
import com.seosh817.kakaoimagesearch.core.common.KakaoImageSearchDispatchers
import com.seosh817.kakaoimagesearch.core.data.local.source.BookmarkLocalDataSource
import com.seosh817.kakaoimagesearch.core.data.mapper.asDomainEntity
import com.seosh817.kakaoimagesearch.core.data.mapper.asLocalEntity
import com.seosh817.kakaoimagesearch.core.data.paging.ImageSearchPagingSource
import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkLocalDataSource: BookmarkLocalDataSource,
    @Dispatcher(KakaoImageSearchDispatchers.IO) private val dispatcher: CoroutineDispatcher
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

    override fun getBookmarksByQuery(query: String): Flow<PagingData<Bookmark>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                bookmarkLocalDataSource
                    .getBookmarksByQuery(query)
            }
        )
            .flow
            .map { pagingData ->
                pagingData.map { imageDto ->
                    imageDto.asDomainEntity()
                }
            }
            .flowOn(dispatcher)
    }

    override suspend fun insertBookmark(bookmark: Bookmark) {
        return bookmarkLocalDataSource.insertBookmark(bookmark.asLocalEntity())
    }

    override suspend fun deleteBookmark(urls: List<String>) {
        return bookmarkLocalDataSource.deleteBookmark(urls)
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}
