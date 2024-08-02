package com.seosh817.kakaoimagesearch.bookmarks.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.seosh817.kakaoimagesearch.bookmarks.BookmarksRoute

const val bookmarksNavigationRoute = "bookmarks_route"

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    navigate(bookmarksNavigationRoute, navOptions)
}

fun NavGraphBuilder.bookmarksScreen(
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
) {
    composable(
        route = bookmarksNavigationRoute,
    ) {
        BookmarksRoute(
            onShowSnackbar = onShowSnackbar,
        )
    }
}
