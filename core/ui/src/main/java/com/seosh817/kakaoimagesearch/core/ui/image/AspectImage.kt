package com.seosh817.kakaoimagesearch.core.ui.image

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage

@Composable
fun AspectImage(
    modifier: Modifier = Modifier,
    userImage: UserImage,
    contentDescription: String? = null,
) {
    val aspectRatio = userImage.width.toFloat() / userImage.height.toFloat()
    var currentUrl by remember(userImage.imageUrl) { mutableStateOf(userImage.imageUrl) }

    Card(
        modifier = modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        AsyncImage(
            modifier = modifier
                .aspectRatio(aspectRatio),
            model = ImageRequest.Builder(LocalContext.current)
                .data(currentUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            onError = {
                if (userImage.imageUrl != userImage.thumbnailUrl) {
                    currentUrl = userImage.thumbnailUrl
                }
            }
        )
    }
}