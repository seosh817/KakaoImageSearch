package com.seosh817.kakaoimagesearch.domain.usecase.bookmark

import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import javax.inject.Inject


class InsertBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: Bookmark) = bookmarkRepository.insertBookmark(bookmark)
}
