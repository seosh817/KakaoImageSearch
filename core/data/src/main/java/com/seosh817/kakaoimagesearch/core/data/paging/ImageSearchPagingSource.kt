package com.seosh817.kakaoimagesearch.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seosh817.common.network.exception.NetworkException
import com.seosh817.common.result.ResultState
import com.seosh817.common.result.extension.map
import com.seosh817.kakaoimagesearch.core.data.remote.model.ImageSearchResponseDto
import com.seosh817.kakaoimagesearch.core.data.remote.model.SearchImageDto

class ImageSearchPagingSource(
    private val loader: suspend (Int) -> ResultState<ImageSearchResponseDto>
) : PagingSource<Int, SearchImageDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchImageDto> {
        val page = params.key ?: INITIAL_PAGE_INDEX

        return loader
            .invoke(page)
            .map(
                {
                    if (it.data.searchImages.isNotEmpty()) {
                        LoadResult.Page(
                            data = it.data.searchImages,
                            prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                            nextKey = page + 1
                        )
                    } else {
                        LoadResult.Error(NoSuchElementException("query result is empty"))
                    }
                }, {
                    when (it) {
                        is ResultState.Failure.Error -> LoadResult.Error(NetworkException(it.code, it.message))
                        is ResultState.Failure.Exception -> LoadResult.Error(it.e)
                    }
                }
            )
    }

    override fun getRefreshKey(state: PagingState<Int, SearchImageDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }
}
