package com.seosh817.kakaoimagesearch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.seosh817.kakaoimagesearch.bookmarks.navigation.bookmarksScreen
import com.seosh817.kakaoimagesearch.search.navigation.searchNavigationRoute
import com.seosh817.kakaoimagesearch.search.navigation.searchScreen

@Composable
fun KakaoImageSearchNavHost(
    modifier: Modifier = Modifier,
    kakaoImageSearchNavigator: KakaoImageSearchNavigator,
    startDestination: String = searchNavigationRoute
) {
    val navController = kakaoImageSearchNavigator.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        searchScreen()
        bookmarksScreen()
    }
}
