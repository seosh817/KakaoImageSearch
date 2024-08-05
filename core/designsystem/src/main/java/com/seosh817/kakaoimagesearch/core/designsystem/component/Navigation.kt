package com.seosh817.kakaoimagesearch.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seosh817.kakaoimagesearch.core.designsystem.theme.KakaoImageSearchTheme
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationBar(
    modifier: Modifier = Modifier,
    scrollBehavior: BottomAppBarScrollBehavior,
    content: @Composable RowScope.() -> Unit,
) {
    BottomAppBar(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 0.dp,
        content = content,
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun RowScope.MainNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = false,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = KakaoImageSearchNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = KakaoImageSearchNavigationDefaults.navigationContentColor(),
            selectedTextColor = KakaoImageSearchNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = KakaoImageSearchNavigationDefaults.navigationContentColor(),
            indicatorColor = KakaoImageSearchNavigationDefaults.navigationIndicatorColor(),
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun MainNavigationPreview() {
    val items = listOf("Search", "Bookmarks")
    val icons = listOf(
        Icons.Outlined.Search,
        Icons.Outlined.Favorite
    )
    val selectedIcons = listOf(
        Icons.Filled.Search,
        Icons.Filled.Favorite,
    )

    KakaoImageSearchTheme {
        MainNavigationBar(
            scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(),
        ) {
            items.forEachIndexed { index, item ->
                MainNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = {},
                )
            }
        }
    }
}

object KakaoImageSearchNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.inversePrimary
}
