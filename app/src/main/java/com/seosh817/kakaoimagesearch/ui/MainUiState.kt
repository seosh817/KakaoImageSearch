package com.seosh817.kakaoimagesearch.ui

sealed interface MainUiState {
    data object Success : MainUiState
    data object Loading : MainUiState
}
