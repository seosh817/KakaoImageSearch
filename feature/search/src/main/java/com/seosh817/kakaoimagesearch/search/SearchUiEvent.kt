package com.seosh817.kakaoimagesearch.search

import com.seosh817.kakaoimagesearch.domain.entity.SearchImage

sealed interface SearchUiEvent {

    data class OnQueryChanged(val query: String) : SearchUiEvent

    data object ClearSearchQuery : SearchUiEvent

    data class OnBookmarkClick(val searchImage: SearchImage, val isBookmark: Boolean) : SearchUiEvent
}