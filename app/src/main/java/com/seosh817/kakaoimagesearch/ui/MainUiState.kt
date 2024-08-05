package com.seosh817.kakaoimagesearch.ui

import com.seosh817.kakaoimagesearch.domain.entity.AppSettings
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode

sealed interface MainUiState {
    data class Success(val appSettings: AppSettings) : MainUiState
    data object Loading : MainUiState
}
