package com.seosh817.kakaoimagesearch.core.ui.grid_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seosh817.kakaoimagesearch.core.designsystem.component.BookmarkToggleButton
import com.seosh817.kakaoimagesearch.core.designsystem.theme.KakaoImageSearchTheme
import com.seosh817.kakaoimagesearch.core.designsystem.theme.LocalAppDimens
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews
import com.seosh817.kakaoimagesearch.core.ui.R
import com.seosh817.kakaoimagesearch.core.ui.image.AspectImage
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage

@Composable
fun SelectableGridItem(
    modifier: Modifier = Modifier,
    userImage: UserImage,
    isSelected: Boolean = false,
    onSelected: (Boolean) -> Unit
) {
    val aspectRatio = userImage.width.toFloat() / userImage.height.toFloat()

    Box(
        modifier = modifier
            .aspectRatio(aspectRatio)
            .fillMaxSize()
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                onClick = {
                    onSelected(isSelected)
                },
                enabled = true
            )
            .alpha(if (isSelected) 0.5f else 1f),
    ) {
        AspectImage(
            modifier = Modifier,
            userImage = userImage,
            contentDescription = stringResource(id = R.string.search_grid_item_image_content_description)
        )
    }
}

@ThemePreviews
@Composable
fun BookmarksGridItemPreview() {

    val isSelected = remember { mutableStateOf(true) }

    val searchImage = UserImage(
        imageUrl = "https://example.com/image.jpg",
        thumbnailUrl = "https://example.com/thumbnail.jpg",
        width = 200,
        height = 300,
        collection = "sample",
        displaySitename = "example.com",
        docUrl = "https://example.com/doc",
        datetime = "2021-01-01T00:00:00.000+09:00",
        isBookmarked = true
    )

    KakaoImageSearchTheme {
        SelectableGridItem(
            userImage = searchImage,
            isSelected = isSelected.value,
            onSelected = { selected ->
                isSelected.value = selected
                println("isSelected is now: $selected")
            },
        )
    }
}
