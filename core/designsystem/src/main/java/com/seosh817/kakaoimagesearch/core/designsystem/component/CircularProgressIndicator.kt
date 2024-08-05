package com.seosh817.kakaoimagesearch.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seosh817.kakaoimagesearch.core.designsystem.R
import com.seosh817.kakaoimagesearch.core.designsystem.theme.KakaoImageSearchTheme
import com.seosh817.kakaoimagesearch.core.designsystem.theme.LocalAppDimens
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews

@Composable
fun MainCircularProgressIndicator(
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Column(
        modifier = modifier
            .padding(vertical = LocalAppDimens.current.PaddingSmall),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text ?: stringResource(id = R.string.now_loading),
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
    }
}

@ThemePreviews
@Composable
fun MainCircularProgressIndicatorPreview() {
    KakaoImageSearchTheme {
        MainCircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}
