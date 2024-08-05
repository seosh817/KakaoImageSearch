package com.seosh817.kakaoimagesearch.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.seosh817.kakaoimagesearch.core.designsystem.R

object AppDimens {

    val PaddingSmall: Dp
        @Composable get() = dimensionResource(R.dimen.padding_small)

    val PaddingNormal: Dp
        @Composable get() = dimensionResource(R.dimen.padding_normal)

    val PaddingLarge: Dp
        @Composable get() = dimensionResource(R.dimen.padding_large)

    val PaddingExtraLarge: Dp
        @Composable get() = dimensionResource(R.dimen.padding_extra_large)
}

val LocalAppDimens = staticCompositionLocalOf { AppDimens }
