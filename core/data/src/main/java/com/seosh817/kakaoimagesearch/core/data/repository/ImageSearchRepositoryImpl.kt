package com.seosh817.kakaoimagesearch.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.seosh817.kakaoimagesearch.core.common.Dispatcher
import com.seosh817.kakaoimagesearch.core.common.KakaoImageSearchDispatchers
import com.seosh817.kakaoimagesearch.core.data.paging.ImageSearchPagingSource
import com.seosh817.kakaoimagesearch.core.data.remote.source.ImageSearchRemoteDataSource
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import com.seosh817.kakaoimagesearch.domain.repository.ImageSearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val imageSearchRemoteDataSource: ImageSearchRemoteDataSource,
    @Dispatcher(KakaoImageSearchDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : ImageSearchRepository {

    override fun searchImage(query: String): Flow<PagingData<SearchImage>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImageSearchPagingSource { page ->
                    imageSearchRemoteDataSource.fetchImageSearch(query, page)
                }
            }
        )
            .flow
            .map { pagingData ->
                pagingData.map { imageDto ->
                    imageDto.asEntity()
                }
            }
            .flowOn(dispatcher)
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}