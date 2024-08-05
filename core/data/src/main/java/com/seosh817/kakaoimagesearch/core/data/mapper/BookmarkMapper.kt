package com.seosh817.kakaoimagesearch.core.data.mapper

import com.seosh817.kakaoimagesearch.core.data.local.model.BookmarkEntity
import com.seosh817.kakaoimagesearch.domain.entity.Bookmark

fun BookmarkEntity.asDomainEntity() = Bookmark(
    collection = collection,
    datetime = datetime,
    displaySiteName = displaySiteName,
    docUrl = docUrl,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    width = width,
    height = height,
    query = query
)

fun Bookmark.asLocalEntity() = BookmarkEntity(
    collection = collection,
    datetime = datetime,
    displaySiteName = displaySiteName,
    docUrl = docUrl,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    width = width,
    height = height,
    query = query
)
