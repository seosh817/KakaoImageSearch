package com.seosh817.kakaoimagesearch.search

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
import com.seosh817.kakaoimagesearch.feature.search.R
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val searchPagingItems: LazyPagingItems<UserImage> = viewModel.searchImagePagingItems.collectAsLazyPagingItems()
    val query by viewModel.queryStateFlow.collectAsState("")

    SearchScreen(
        modifier = modifier,
        searchPagingItems = searchPagingItems,
        query = query,
        onTextChanged = {
            viewModel.handleSearchUiEvent(SearchUiEvent.OnQueryChanged(it))
        },
        onClearIconClick = {
            viewModel.handleSearchUiEvent(SearchUiEvent.ClearSearchQuery)
        },
        onClickBookmark = { userImage, bookmarked ->
            viewModel.handleSearchUiEvent(SearchUiEvent.OnBookmarkClick(userImage, bookmarked))
        },
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    searchPagingItems: LazyPagingItems<UserImage>,
    query: String,
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
            hint = stringResource(id = R.string.enter_keyword),
            onValueChanged = onTextChanged,
            onClickClearKeyword = onClearIconClick
        )

        SearchLazyVerticalGrid(
            modifier = modifier,
            query = query,
            searchPagingItems = searchPagingItems,
            onClickBookmark = onClickBookmark
        )
    }
}

@Composable
fun SearchLazyVerticalGrid(
    modifier: Modifier,
    query: String,
    searchPagingItems: LazyPagingItems<UserImage>,
    onClickBookmark: (UserImage, Boolean) -> Unit,
) {

    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    if (query.isEmpty()) {
        ConfirmErrorContents(
            modifier = modifier.fillMaxSize(),
            message = stringResource(id = R.string.search_empty),
        )
    } else {
        when (searchPagingItems.loadState.refresh) {
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
                val error = (searchPagingItems.loadState.refresh as LoadState.Error).error
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
                        count = searchPagingItems.itemCount,
                        key = searchPagingItems.itemKey(),
                        contentType = searchPagingItems.itemContentType()
                    ) { index ->
                        val userImage: UserImage? = searchPagingItems[index]
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
fun SearchScreenPreview() {
    SearchScreen(
        query = "Hello world!",
        searchPagingItems = flowOf(PagingData.empty<UserImage>()).collectAsLazyPagingItems(),
    ) { _, _ -> }
}