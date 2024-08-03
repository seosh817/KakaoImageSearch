package com.seosh817.kakaoimagesearch.data.remote.source.impl

import com.seosh817.common.network.handleApi
import com.seosh817.common.result.ResultState
import com.seosh817.kakaoimagesearch.data.remote.model.ImageSearchResponseDto
import com.seosh817.kakaoimagesearch.data.remote.service.search.ImageSearchService
import com.seosh817.kakaoimagesearch.data.remote.source.ImageSearchRemoteDataSource
import javax.inject.Inject

class ImageSearchRemoteDataSourceImpl @Inject constructor(
    private val imageSearchService: ImageSearchService
) : ImageSearchRemoteDataSource {

    override suspend fun fetchImageSearch(query: String, page: Int): ResultState<ImageSearchResponseDto> {
        return handleApi {
            imageSearchService.fetchImageSearch(query, page)
        }
    }
}
