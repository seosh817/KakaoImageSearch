package com.seosh817.kakaoimagesearch.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.seosh817.kakaoimagesearch.search.SearchRoute

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(
        route = searchNavigationRoute,
    ) {
        SearchRoute()
    }
}
