package com.seosh817.kakaoimagesearch.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.seosh817.kakaoimagesearch.core.designsystem.R
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable RowScope.() -> Unit,
) {
    Surface {
        TopAppBar(
            modifier = modifier
                .statusBarsPadding()
                .background(color = MaterialTheme.colorScheme.surface),
            actions = actions,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = KakaoImageSearchAppBarDefaults.titleContentColor(),
                actionIconContentColor = KakaoImageSearchAppBarDefaults.actionIconContentColor(),
            )
        )
    }
}

@ThemePreviews
@Composable
fun MainTopAppBarPreview() {
    MainTopAppBar(
        title = "검색",
        actions = {
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Default.DarkMode,
                    contentDescription = "Dark Mode Icon Preview"
                )
            }
        }
    )
}

object KakaoImageSearchAppBarDefaults {
    @Composable
    fun titleContentColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun actionIconContentColor() = MaterialTheme.colorScheme.onSurfaceVariant
}