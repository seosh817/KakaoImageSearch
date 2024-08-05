package com.seosh817.kakaoimagesearch.domain.entity

data class Bookmark(
    val collection: String,
    val datetime: String,
    val displaySiteName: String,
    val docUrl: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val width: Int,
    val height: Int,
    val query: String
)