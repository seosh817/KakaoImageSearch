package com.seosh817.kakaoimagesearch.bookmarks

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun BookmarksRoute(
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
) {
    BookmarksScreen(
        modifier = modifier,
        onShowSnackbar = onShowSnackbar,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun BookmarksScreen(
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
) {
}