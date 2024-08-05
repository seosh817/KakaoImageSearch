package com.seosh817.kakaoimagesearch.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.seosh817.kakaoimagesearch.R

enum class PrimaryDestination(
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val contentDescription: String,
    @StringRes val labelResId: Int,
) {
    SEARCH(
        selectedIcon = Icons.Filled.Search,
        unSelectedIcon = Icons.Outlined.Search,
        contentDescription = "Search",
        labelResId = R.string.search
    ),
    BOOKMARKS(
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.Favorite,
        contentDescription = "Bookmarks",
        labelResId = R.string.bookmarks
    ),
}
