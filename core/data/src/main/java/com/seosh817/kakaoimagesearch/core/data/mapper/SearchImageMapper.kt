package com.seosh817.kakaoimagesearch.core.data.mapper

import com.seosh817.kakaoimagesearch.core.data.remote.model.SearchImageDto
import com.seosh817.kakaoimagesearch.domain.entity.SearchImage

fun SearchImageDto.asDomainEntity() = SearchImage(
    collection = collection,
    datetime = datetime,
    displaySitename = displaySitename,
    docUrl = docUrl,
    height = height,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    width = width
)

fun SearchImage.asRemoteDto() = SearchImageDto(
    collection = collection,
    datetime = datetime,
    displaySitename = displaySitename,
    docUrl = docUrl,
    height = height,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    width = width
)
