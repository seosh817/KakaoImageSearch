package com.seosh817.kakaoimagesearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seosh817.kakaoimagesearch.domain.entity.AppLanguage
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode
import com.seosh817.kakaoimagesearch.domain.repository.AppSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = appSettingsRepository.appSettings
        .map {
            MainUiState.Success(it)
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = MainUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )

    fun updateDarkThemeMode(darkThemeMode: DarkThemeMode) {
        viewModelScope.launch {
            appSettingsRepository.setDarkThemeMode(darkThemeMode)
        }
    }

    fun updateAppLanguage(appLanguage: AppLanguage) {
        viewModelScope.launch {
            appSettingsRepository.setAppLanguage(appLanguage)
        }
    }
}