package com.seosh817.kakaoimagesearch.domain.repository

import androidx.paging.PagingData
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import kotlinx.coroutines.flow.Flow

interface ImageSearchRepository {

    fun searchImage(query: String): Flow<PagingData<SearchImage>>
}