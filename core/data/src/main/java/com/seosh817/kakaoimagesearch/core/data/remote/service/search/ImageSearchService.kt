package com.seosh817.kakaoimagesearch.core.data.remote.service.search

import com.seosh817.kakaoimagesearch.core.data.remote.model.ImageSearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchService {

    @GET("search/image")
    suspend fun fetchImageSearch(@Query("query") query: String, @Query("page") page: Int): Response<ImageSearchResponseDto>
}
