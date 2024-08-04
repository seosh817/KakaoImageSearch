package com.seosh817.kakaoimagesearch.bookmarks

import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage

sealed interface BookmarksUiEvent {

    data class OnQueryChanged(val query: String) : BookmarksUiEvent

    data object ClearSearchQuery : BookmarksUiEvent

    data class OnBookmarkClick(val userImage: UserImage, val isBookmark: Boolean) : BookmarksUiEvent
}