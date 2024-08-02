package com.seosh817.kakaoimagesearch.search

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
) {
    SearchScreen(
        modifier = modifier,
        onShowSnackbar = onShowSnackbar,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
) {
}