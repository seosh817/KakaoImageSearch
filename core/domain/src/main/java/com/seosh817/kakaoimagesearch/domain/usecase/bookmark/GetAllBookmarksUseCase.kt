package com.seosh817.kakaoimagesearch.domain.usecase.bookmark

import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBookmarksUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    operator fun invoke(): Flow<List<Bookmark>> = bookmarkRepository.getAllBookmarks()
}
