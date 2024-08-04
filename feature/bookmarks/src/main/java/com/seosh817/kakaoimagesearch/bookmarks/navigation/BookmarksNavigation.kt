package com.seosh817.kakaoimagesearch.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.seosh817.kakaoimagesearch.bookmarks.BookmarksRoute

const val bookmarksNavigationRoute = "bookmarks_route"

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    navigate(bookmarksNavigationRoute, navOptions)
}

fun NavGraphBuilder.bookmarksScreen() {
    composable(
        route = bookmarksNavigationRoute,
    ) {
        BookmarksRoute()
    }
}
