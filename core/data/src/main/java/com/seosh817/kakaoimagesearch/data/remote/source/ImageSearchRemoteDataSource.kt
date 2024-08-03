package com.seosh817.kakaoimagesearch.data.remote.source

import com.seosh817.common.result.ResultState
import com.seosh817.kakaoimagesearch.data.remote.model.ImageSearchResponseDto

interface ImageSearchRemoteDataSource {

    suspend fun fetchImageSearch(query: String, page: Int): ResultState<ImageSearchResponseDto>
}
