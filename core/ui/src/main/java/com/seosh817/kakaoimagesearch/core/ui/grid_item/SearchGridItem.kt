package com.seosh817.kakaoimagesearch.core.ui.grid_item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.seosh817.kakaoimagesearch.core.designsystem.component.BookmarkToggleButton
import com.seosh817.kakaoimagesearch.core.designsystem.theme.KakaoImageSearchTheme
import com.seosh817.kakaoimagesearch.core.designsystem.theme.LocalAppDimens
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews
import com.seosh817.kakaoimagesearch.core.ui.R
import com.seosh817.kakaoimagesearch.core.ui.image.AspectImage
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage

@Composable
fun SearchGridItem(
    modifier: Modifier = Modifier,
    userImage: UserImage,
    onClickBookmark: (Boolean) -> Unit
) {
    val aspectRatio = userImage.width.toFloat() / userImage.height.toFloat()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
    ) {
        AspectImage(
            modifier = modifier.fillMaxSize(),
            userImage = userImage,
            contentDescription = stringResource(id = R.string.search_grid_item_image_content_description)
        )

        BookmarkToggleButton(
            modifier = Modifier
                .padding(LocalAppDimens.current.PaddingSmall)
                .align(Alignment.TopEnd),
            checked = userImage.isBookmarked,
            onCheckedClick = onClickBookmark
        )
    }
}

@ThemePreviews
@Composable
fun SearchGridItemPreview() {
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
        SearchGridItem(
            userImage = searchImage,
            onClickBookmark = { },
        )
    }
}
