package com.seosh817.kakaoimagesearch.bookmarks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage
import com.seosh817.kakaoimagesearch.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.seosh817.kakaoimagesearch.domain.usecase.bookmark.GetBookmarksByQueryUseCase
import com.seosh817.kakaoimagesearch.domain.usecase.bookmark.InsertBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val getBookmarksByQueryUseCase: GetBookmarksByQueryUseCase,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : ViewModel() {

    private val _bookmarksUiState = MutableStateFlow<BookmarksUiState>(BookmarksUiState.NormalMode)
    val bookmarksUiState: StateFlow<BookmarksUiState> = _bookmarksUiState.asStateFlow()

    private val _queryStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val queryStateFlow: StateFlow<String> = _queryStateFlow.asStateFlow()

    private val _selectedBookmarks: MutableStateFlow<Set<UserImage>> = MutableStateFlow(emptySet())
    val selectedBookmarks: StateFlow<Set<UserImage>> = _selectedBookmarks.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val bookmarkPagingItems: Flow<PagingData<UserImage>> = _queryStateFlow
        .debounce(DEBOUNCE_TIME)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            getBookmarksByQueryUseCase(query)
                .cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { bookmark ->
                        UserImage.fromBookMark(bookmark)
                    }
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PagingData.empty()
        )

    fun handleBookmarksUiEvent(bookmarksUiEvent: BookmarksUiEvent) {
        when (bookmarksUiEvent) {
            is BookmarksUiEvent.OnQueryChanged -> onQueryChanged(bookmarksUiEvent.query)
            is BookmarksUiEvent.ClearSearchQuery -> onQueryChanged("")
            is BookmarksUiEvent.OnBookmarkClick -> onClickBookmark(bookmarksUiEvent.userImage, bookmarksUiEvent.isBookmark)
            is BookmarksUiEvent.OnDeleteClick -> onDeleteClick()
            is BookmarksUiEvent.OnEditClick -> onEditClick()
            is BookmarksUiEvent.OnCancelClick -> onCancelClick()
            is BookmarksUiEvent.OnSelected -> onSelected(bookmarksUiEvent.userImage)
        }
    }

    private fun onQueryChanged(query: String) {
        _queryStateFlow.value = query
    }

    private fun onSelected(bookmark: UserImage) {
        _selectedBookmarks.value = _selectedBookmarks.value.toMutableSet().apply {
            if (contains(bookmark)) {
                remove(bookmark)
            } else {
                add(bookmark)
            }
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

    private fun onDeleteClick() {
        viewModelScope.launch {
            try {
                deleteBookmarkUseCase.invoke(_selectedBookmarks.value.map { it.imageUrl })
                _bookmarksUiState.value = BookmarksUiState.NormalMode
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun onEditClick() {
        _bookmarksUiState.value = BookmarksUiState.EditMode
    }

    private fun onCancelClick() {
        _bookmarksUiState.value = BookmarksUiState.NormalMode
        _selectedBookmarks.value = emptySet()
    }

    companion object {
        private const val DEBOUNCE_TIME = 1_000L
    }
}