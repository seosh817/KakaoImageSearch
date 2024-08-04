package com.seosh817.kakaoimagesearch.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.seosh817.kakaoimagesearch.core.ui.grid_item.SelectableGridItem
import com.seosh817.kakaoimagesearch.domain.entity.composite.UserImage
import com.seosh817.kakaoimagesearch.feature.bookmarks.R
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun BookmarksRoute(
    modifier: Modifier = Modifier,
    viewModel: BookmarksViewModel = hiltViewModel(),
) {

    val bookmarksUiState by viewModel.bookmarksUiState.collectAsState(BookmarksUiState.NormalMode)
    val bookmarkPagingItems: LazyPagingItems<UserImage> = viewModel.bookmarkPagingItems.collectAsLazyPagingItems()
    val selectedBookmarks by viewModel.selectedBookmarks.collectAsState(emptySet())
    val query by viewModel.queryStateFlow.collectAsState("")

    BookmarksScreen(
        modifier = modifier,
        bookmarksUiState = bookmarksUiState,
        bookmarkPagingItems = bookmarkPagingItems,
        query = query,
        selectedBookmarks = selectedBookmarks,
        onTextChanged = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnQueryChanged(it))
        },
        onClearIconClick = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.ClearSearchQuery)
        },
        onClickBookmark = { userImage, bookmarked ->
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnBookmarkClick(userImage, bookmarked))
        },
        onClickDelete = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnDeleteClick)
        },
        onClickEdit = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnEditClick)
        },
        onClickCancel = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnCancelClick)
        },
        onSelectedImage = {
            viewModel.handleBookmarksUiEvent(BookmarksUiEvent.OnSelected(it))
        }
    )
}

@Composable
internal fun BookmarksScreen(
    modifier: Modifier = Modifier,
    bookmarksUiState: BookmarksUiState,
    bookmarkPagingItems: LazyPagingItems<UserImage>,
    query: String,
    selectedBookmarks: Set<UserImage>,
    onTextChanged: (String) -> Unit = {},
    onClearIconClick: () -> Unit = {},
    onClickBookmark: (UserImage, Boolean) -> Unit,
    onClickDelete: () -> Unit,
    onClickEdit: () -> Unit,
    onClickCancel: () -> Unit,
    onSelectedImage: (UserImage) -> Unit
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

        BookmarksContents(
            modifier = modifier,
            bookmarksUiState = bookmarksUiState,
            bookmarksPagingItems = bookmarkPagingItems,
            selectedBookmarks = selectedBookmarks,
            onClickBookmark = onClickBookmark,
            onClickDelete = onClickDelete,
            onClickEdit = onClickEdit,
            onClickCancel = onClickCancel,
            onSelectedImage = onSelectedImage
        )
    }
}

@Composable
fun BookmarksContents(
    modifier: Modifier,
    bookmarksUiState: BookmarksUiState,
    bookmarksPagingItems: LazyPagingItems<UserImage>,
    selectedBookmarks: Set<UserImage>,
    onClickBookmark: (UserImage, Boolean) -> Unit,
    onClickDelete: () -> Unit,
    onClickEdit: () -> Unit,
    onClickCancel: () -> Unit,
    onSelectedImage: (UserImage) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BookmarksEditModeButtons(
            modifier = modifier,
            bookmarksUiState = bookmarksUiState,
            onClickDelete = onClickDelete,
            onClickEdit = onClickEdit,
            onClickCancel = onClickCancel
        )

        BookmarksLazyVerticalGrid(
            modifier = modifier,
            bookmarksUiState = bookmarksUiState,
            bookmarksPagingItems = bookmarksPagingItems,
            selectedBookmarks = selectedBookmarks,
            onClickBookmark = onClickBookmark,
            onSelectImage = onSelectedImage,
        )
    }
}

@Composable
fun BookmarksEditModeButtons(
    modifier: Modifier,
    bookmarksUiState: BookmarksUiState,
    onClickDelete: () -> Unit,
    onClickEdit: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.End
    ) {
        if (bookmarksUiState is BookmarksUiState.EditMode) {
            TextButton(onClick = onClickDelete) {
                Text(
                    text = stringResource(id = R.string.delete),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            TextButton(onClick = onClickCancel) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        } else {
            TextButton(onClick = onClickEdit) {
                Text(
                    text = stringResource(id = R.string.edit),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun BookmarksLazyVerticalGrid(
    modifier: Modifier,
    bookmarksUiState: BookmarksUiState,
    bookmarksPagingItems: LazyPagingItems<UserImage>,
    selectedBookmarks: Set<UserImage>,
    onClickBookmark: (UserImage, Boolean) -> Unit,
    onSelectImage: (UserImage) -> Unit = {},
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
                            if (bookmarksUiState is BookmarksUiState.EditMode) {
                                SelectableGridItem(
                                    modifier = modifier
                                        .fillMaxSize()
                                        .clip(MaterialTheme.shapes.small)
                                        .background(MaterialTheme.colorScheme.background),
                                    userImage = userImage,
                                    isSelected = selectedBookmarks.contains(userImage),
                                    onSelected = {
                                        onSelectImage.invoke(userImage)
                                    }
                                )
                            } else {
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
}

@ThemePreviews
@Composable
fun BookmarksScreenPreview() {
    BookmarksScreen(
        query = "Hello world!",
        bookmarksUiState = BookmarksUiState.NormalMode,
        bookmarkPagingItems = flowOf(PagingData.empty<UserImage>()).collectAsLazyPagingItems(),
        selectedBookmarks = emptySet(),
        onTextChanged = {},
        onClearIconClick = {},
        onClickBookmark = { _, _ -> },
        onClickDelete = {},
        onClickEdit = {},
        onClickCancel = {},
        onSelectedImage = {}
    )
}