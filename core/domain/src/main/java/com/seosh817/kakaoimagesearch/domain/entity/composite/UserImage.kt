package com.seosh817.kakaoimagesearch.domain.entity.composite

import com.seosh817.kakaoimagesearch.domain.entity.Bookmark
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage

data class UserImage(
    var collection: String,
    var datetime: String,
    var displaySitename: String,
    var docUrl: String,
    var height: Int,
    var imageUrl: String,
    var thumbnailUrl: String,
    var width: Int,
    var isBookmarked: Boolean = false
) {
    constructor (searchImage: SearchImage, isBookmarked: Boolean) : this(
        searchImage.collection,
        searchImage.datetime,
        searchImage.displaySitename,
        searchImage.docUrl,
        searchImage.height,
        searchImage.imageUrl,
        searchImage.thumbnailUrl,
        searchImage.width,
        isBookmarked
    )

    fun toBookmark(query: String) = Bookmark(
        collection = collection,
        datetime = datetime,
        displaySiteName = displaySitename,
        docUrl = docUrl,
        imageUrl = imageUrl,
        thumbnailUrl = thumbnailUrl,
        width = width,
        height = height,
        query = query
    )

    companion object {
        fun fromBookMark(bookmark: Bookmark) = UserImage(
            collection = bookmark.collection,
            datetime = bookmark.datetime,
            displaySitename = bookmark.displaySiteName,
            docUrl = bookmark.docUrl,
            height = bookmark.height,
            imageUrl = bookmark.imageUrl,
            thumbnailUrl = bookmark.thumbnailUrl,
            width = bookmark.width,
            isBookmarked = true
        )
    }
}
