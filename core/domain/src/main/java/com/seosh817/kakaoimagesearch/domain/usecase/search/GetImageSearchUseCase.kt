package com.seosh817.kakaoimagesearch.domain.usecase.search

import androidx.paging.PagingData
import com.seosh817.kakaoimagesearch.core.common.Dispatcher
import com.seosh817.kakaoimagesearch.core.common.KakaoImageSearchDispatchers
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import com.seosh817.kakaoimagesearch.domain.repository.ImageSearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetImageSearchUseCase @Inject constructor(
    private val imageSearchRepository: ImageSearchRepository,
    @Dispatcher(KakaoImageSearchDispatchers.IO) private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(query: String): Flow<PagingData<SearchImage>> = imageSearchRepository
        .searchImage(query)
        .flowOn(dispatcher)
}
