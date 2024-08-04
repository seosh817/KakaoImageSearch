package com.seosh817.kakaoimagesearch.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage
import com.seosh817.kakaoimagesearch.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.seosh817.kakaoimagesearch.domain.usecase.bookmark.GetAllBookmarksUseCase
import com.seosh817.kakaoimagesearch.domain.usecase.bookmark.InsertBookmarkUseCase
import com.seosh817.kakaoimagesearch.domain.usecase.search.GetImageSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImageSearchUseCase: GetImageSearchUseCase,
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : ViewModel() {

    private val _queryStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val queryStateFlow: Flow<String> = _queryStateFlow.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchImagePagingItems: Flow<PagingData<UserImage>> = _queryStateFlow
        .debounce(DEBOUNCE_TIME)
        .filter {
            it.trim().isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            combine(
                getImageSearchUseCase(query)
                    .cachedIn(viewModelScope),
                getAllBookmarksUseCase()
            )
            { pagingData, bookmarks ->
                pagingData.map { searchImage ->
                    UserImage(searchImage = searchImage, isBookmarked = bookmarks.any {
                        searchImage.isBookmarked(it)
                    })
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PagingData.empty()
        )

    private fun onQueryChanged(query: String) {
        _queryStateFlow.value = query
    }

    fun handleSearchUiEvent(searchUiEvent: SearchUiEvent) {
        when (searchUiEvent) {
            is SearchUiEvent.OnQueryChanged -> onQueryChanged(searchUiEvent.query)
            is SearchUiEvent.OnBookmarkClick -> onClickBookmark(searchUiEvent.userImage, searchUiEvent.isBookmark)
            is SearchUiEvent.ClearSearchQuery -> onQueryChanged("")
        }

    }

    private fun onClickBookmark(userImage: UserImage, isBookmarked: Boolean) = viewModelScope.launch {
        if (isBookmarked) {
            try {
                deleteBookmarkUseCase.invoke(listOf(userImage.imageUrl))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                insertBookmarkUseCase.invoke(userImage.toBookmark(_queryStateFlow.value))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val DEBOUNCE_TIME = 1_000L
    }
}