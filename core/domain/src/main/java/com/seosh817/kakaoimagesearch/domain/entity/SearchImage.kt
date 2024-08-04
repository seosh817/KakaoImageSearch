package com.seosh817.kakaoimagesearch.domain.entity

data class SearchImage(
    var collection: String,
    var datetime: String,
    var displaySitename: String,
    var docUrl: String,
    var height: Int,
    var imageUrl: String,
    var thumbnailUrl: String,
    var width: Int
) {

    fun isBookmarked(bookmark: Bookmark): Boolean {
        return this.imageUrl == bookmark.imageUrl
    }
}
