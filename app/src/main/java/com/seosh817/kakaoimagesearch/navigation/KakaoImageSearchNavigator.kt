package com.seosh817.kakaoimagesearch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.seosh817.kakaoimagesearch.bookmarks.navigation.bookmarksNavigationRoute
import com.seosh817.kakaoimagesearch.bookmarks.navigation.navigateToBookmarks
import com.seosh817.kakaoimagesearch.search.navigation.searchNavigationRoute
import com.seosh817.kakaoimagesearch.navigation.PrimaryDestination.BOOKMARKS
import com.seosh817.kakaoimagesearch.navigation.PrimaryDestination.SEARCH
import com.seosh817.kakaoimagesearch.search.navigation.navigateToSearch

class KakaoImageSearchNavigator(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentPrimaryDestination: PrimaryDestination?
        @Composable get() = when (currentDestination?.route) {
            searchNavigationRoute -> SEARCH
            bookmarksNavigationRoute -> BOOKMARKS
            else -> null
        }

    fun navigate(mainTab: PrimaryDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (mainTab) {
            SEARCH -> navController.navigateToSearch(navOptions)
            BOOKMARKS -> navController.navigateToBookmarks(navOptions)
        }
    }
}

@Composable
internal fun rememberKakaoImageSearchNavigator(
    navController: NavHostController = rememberNavController(),
): KakaoImageSearchNavigator = remember(navController) {
    KakaoImageSearchNavigator(navController)
}
