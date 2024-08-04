package com.seosh817.kakaoimagesearch.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.seosh817.kakaoimagesearch.core.designsystem.component.MainCircularProgressIndicator
import com.seosh817.kakaoimagesearch.core.designsystem.component.SearchTextField
import com.seosh817.kakaoimagesearch.core.designsystem.theme.LocalAppDimens
import com.seosh817.kakaoimagesearch.core.designsystem.theme.ThemePreviews
import com.seosh817.kakaoimagesearch.core.ui.error.ConfirmErrorContents
import com.seosh817.kakaoimagesearch.core.ui.grid_item.SearchGridItem
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage
import com.seosh817.kakaoimagesearch.feature.bookmarks.R
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun BookmarksRoute(
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
    viewModel: BookmarksViewModel = hiltViewModel(),
) {

    val bookmarkPagingItems: LazyPagingItems<UserImage> = viewModel.bookmarkPagingItems.collectAsLazyPagingItems()
    val query by viewModel.queryStateFlow.collectAsState("")

    BookmarksScreen(
        modifier = modifier,
        bookmarkPagingItems = bookmarkPagingItems,
        query = query,
        onShowSnackbar = onShowSnackbar,
        onTextChanged = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnQueryChanged(it))
        },
        onClearIconClick = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.ClearSearchQuery)
        },
        onClickBookmark = { userImage, bookmarked ->
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnBookmarkClick(userImage, bookmarked))
        },
    )
}

@Composable
internal fun BookmarksScreen(
    modifier: Modifier = Modifier,
    bookmarkPagingItems: LazyPagingItems<UserImage>,
    query: String,
    onShowSnackbar: suspend (String, String?, SnackbarDuration) -> Boolean,
    onTextChanged: (String) -> Unit = {},
    onClearIconClick: () -> Unit = {},
    onClickBookmark: (UserImage, Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SearchTextField(
            modifier = modifier.fillMaxWidth(),
            value = query,
            hint = "Enter Keyword",
            onValueChanged = onTextChanged,
            onClickClearKeyword = onClearIconClick
        )

        BookmarksLazyVerticalGrid(
            modifier = modifier,
            bookmarksPagingItems = bookmarkPagingItems,
            onClickBookmark = onClickBookmark
        )
    }
}

@Composable
fun BookmarksLazyVerticalGrid(
    modifier: Modifier,
    bookmarksPagingItems: LazyPagingItems<UserImage>,
    onClickBookmark: (UserImage, Boolean) -> Unit,
) {

    val lazyStaggeredGridState = rememberLazyStaggeredGridState()


    when (bookmarksPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            MainCircularProgressIndicator(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background
                    )
            )
        }

        is LoadState.Error -> {
            val error = (bookmarksPagingItems.loadState.refresh as LoadState.Error).error
            val errorMessage: String
            if (error is NoSuchElementException) {
                errorMessage = stringResource(id = R.string.no_search_result)
                ConfirmErrorContents(
                    modifier = modifier.fillMaxSize(),
                    message = errorMessage,
                )
            } else {
                errorMessage = error.message ?: stringResource(id = R.string.refresh_error)
                ConfirmErrorContents(
                    modifier = modifier.fillMaxSize(),
                    cause = errorMessage,
                )
            }
        }

        is LoadState.NotLoading -> {
            if (bookmarksPagingItems.itemCount == 0) {
                ConfirmErrorContents(
                    modifier = modifier.fillMaxSize(),
                    message = stringResource(id = R.string.no_search_result),
                )
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(150.dp),
                    state = lazyStaggeredGridState,
                    contentPadding = PaddingValues(
                        horizontal = LocalAppDimens.current.PaddingSmall,
                        vertical = LocalAppDimens.current.PaddingSmall
                    ),
                    verticalItemSpacing = LocalAppDimens.current.PaddingSmall,
                    horizontalArrangement = Arrangement.spacedBy(LocalAppDimens.current.PaddingSmall),
                ) {
                    items(
                        count = bookmarksPagingItems.itemCount,
                        key = bookmarksPagingItems.itemKey(),
                        contentType = bookmarksPagingItems.itemContentType()
                    ) { index ->
                        val userImage: UserImage? = bookmarksPagingItems[index]
                        if (userImage != null) {
                            SearchGridItem(
                                modifier = modifier
                                    .fillMaxSize()
                                    .clip(MaterialTheme.shapes.small)
                                    .background(MaterialTheme.colorScheme.background),
                                userImage = userImage,
                                onClickBookmark = {
                                    onClickBookmark.invoke(userImage, userImage.isBookmarked)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun BookmarksScreenPreview() {
    BookmarksScreen(
        query = "Hello world!",
        bookmarkPagingItems = flowOf(PagingData.empty<UserImage>()).collectAsLazyPagingItems(),
        onShowSnackbar = { _, _, _ -> false }
    ) { _, _ -> }
}