package com.seosh817.kakaoimagesearch.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import com.seosh817.kakaoimagesearch.domain.usecase.GetImageSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImageSearchUseCase: GetImageSearchUseCase
) : ViewModel() {

    private val _queryStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val queryStateFlow: Flow<String> = _queryStateFlow.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchImagePagingItems: Flow<PagingData<SearchImage>> = _queryStateFlow
        .debounce(DEBOUNCE_TIME)
        .filter {
            it.trim().isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            getImageSearchUseCase(query)
                .cachedIn(viewModelScope)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PagingData.empty()
        )

    fun onQueryChanged(query: String) {
        _queryStateFlow.value = query
    }

    fun handleSearchUiEvent(searchUiEvent: SearchUiEvent) {
        when (searchUiEvent) {
            is SearchUiEvent.OnQueryChanged -> onQueryChanged(searchUiEvent.query)
            is SearchUiEvent.OnBookmarkClick -> onClickBookmark(searchUiEvent.searchImage, searchUiEvent.isBookmark)
            is SearchUiEvent.ClearSearchQuery -> onQueryChanged("")
        }

    }

    fun onClickBookmark(searchImage: SearchImage, isBookmarked: Boolean) {

    }

    companion object {
        private const val DEBOUNCE_TIME = 1_000L
    }
}