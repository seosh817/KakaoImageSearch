package com.seosh817.kakaoimagesearch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.BottomAppBarState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.seosh817.kakaoimagesearch.R
import com.seosh817.kakaoimagesearch.core.designsystem.component.MainNavigationBar
import com.seosh817.kakaoimagesearch.core.designsystem.component.MainNavigationBarItem
import com.seosh817.kakaoimagesearch.core.designsystem.component.MainTopAppBar
import com.seosh817.kakaoimagesearch.navigation.KakaoImageSearchNavHost
import com.seosh817.kakaoimagesearch.navigation.KakaoImageSearchNavigator
import com.seosh817.kakaoimagesearch.navigation.PrimaryDestination
import com.seosh817.kakaoimagesearch.navigation.rememberKakaoImageSearchNavigator
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun MainScreen(
    kakaoImageSearchNavigator: KakaoImageSearchNavigator = rememberKakaoImageSearchNavigator(),
    bottomAppBarState: BottomAppBarState = rememberBottomAppBarState(),
) {

    val canScroll by rememberSaveable { mutableStateOf(true) }
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(bottomAppBarState, canScroll = { canScroll })

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            MainBottomBar(
                currentDestination = kakaoImageSearchNavigator.currentDestination,
                destinations = PrimaryDestination
                    .entries
                    .toPersistentList(),
                onTabSelected = {
                    scrollBehavior.state.heightOffset = 0f
                    kakaoImageSearchNavigator.navigate(it)
                },
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            val destination = kakaoImageSearchNavigator.currentPrimaryDestination
            if (destination != null) {
                MainTopAppBar(
                    title = stringResource(destination.labelResId),
                    actions = {
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = stringResource(id = R.string.top_app_bar_dark_mode_icon_description)
                            )
                        }
                    },
                )
            }

            KakaoImageSearchNavHost(
                kakaoImageSearchNavigator = kakaoImageSearchNavigator,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainBottomBar(
    currentDestination: NavDestination?,
    destinations: PersistentList<PrimaryDestination>,
    onTabSelected: (PrimaryDestination) -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: BottomAppBarScrollBehavior,
) {
    MainNavigationBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
    ) {
        destinations.forEach { primaryDestination ->
            MainNavigationBarItem(
                selected = currentDestination?.route?.contains(primaryDestination.name, true) == true,
                onClick = { onTabSelected(primaryDestination) },
                icon = {
                    Icon(
                        imageVector = primaryDestination.unSelectedIcon,
                        contentDescription = primaryDestination.contentDescription,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = primaryDestination.selectedIcon,
                        contentDescription = primaryDestination.contentDescription,
                    )
                },
                label = { Text(stringResource(primaryDestination.labelResId)) },
            )
        }
    }
}
