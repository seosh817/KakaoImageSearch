package com.seosh817.kakaoimagesearch.data.remote.service.search

import com.seosh817.kakaoimagesearch.data.remote.model.ImageSearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchService {

    @GET("/search/image")
    suspend fun fetchImageSearch(@Query("query") query: String, @Query("page") page: Int, @Query("sort") sort: String = "accuracy"): Response<ImageSearchResponseDto>
}
