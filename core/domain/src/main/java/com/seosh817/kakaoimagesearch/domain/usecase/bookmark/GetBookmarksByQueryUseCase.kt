package com.seosh817.kakaoimagesearch.domain.usecase.bookmark

import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksByQueryUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(query: String): Flow<List<Bookmark>> = bookmarkRepository.getBookmarksByQuery(query = query)
}
