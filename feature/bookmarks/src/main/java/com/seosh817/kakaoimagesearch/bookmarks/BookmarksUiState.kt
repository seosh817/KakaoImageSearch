package com.seosh817.kakaoimagesearch.bookmarks

sealed interface BookmarksUiState {

    data object NormalMode : BookmarksUiState

    data object EditMode : BookmarksUiState
}
