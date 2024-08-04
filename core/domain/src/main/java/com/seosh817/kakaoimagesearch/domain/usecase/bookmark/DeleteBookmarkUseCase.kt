package com.seosh817.kakaoimagesearch.domain.usecase.bookmark

import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(urls: List<String>) = bookmarkRepository.deleteBookmark(urls)
}
