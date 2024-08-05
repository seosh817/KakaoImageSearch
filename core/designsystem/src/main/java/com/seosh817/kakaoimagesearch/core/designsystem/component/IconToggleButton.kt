package com.seosh817.kakaoimagesearch.core.designsystem.component

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.seosh817.kakaoimagesearch.core.designsystem.R

@Composable
fun BookmarkToggleButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.Favorite,
    checked: Boolean = false,
    onCheckedClick: ((Boolean) -> Unit)? = null
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            onCheckedClick?.invoke(it)
        },
        modifier = modifier

    ) {
        Icon(
            modifier = modifier,
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.content_description_check_toggle_button),
            tint = if (checked) Color.Red else Color.LightGray
        )
    }
}

@Preview
@Composable
fun LikeToggleButtonNotCheckedPreview() {
    BookmarkToggleButton(checked = false)
}

@Preview
@Composable
fun LikeToggleButtonCheckedPreview() {
    BookmarkToggleButton(checked = true)
}