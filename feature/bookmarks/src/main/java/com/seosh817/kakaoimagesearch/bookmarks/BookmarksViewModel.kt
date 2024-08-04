package com.seosh817.kakaoimagesearch.bookmarks

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

    private val _queryStateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val queryStateFlow: Flow<String> = _queryStateFlow.asStateFlow()

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
        }
    }

    private fun onQueryChanged(query: String) {
        _queryStateFlow.value = query
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