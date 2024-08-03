package com.seosh817.kakaoimagesearch.core.data.remote.model

import com.seosh817.kakaoimagesearch.domain.entity.ImageSearchResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageSearchResponseDto (

    @SerialName("documents")
    var searchImages : List<SearchImageDto>,
    @SerialName("meta")
    var meta : MetaDto
) {
    fun asEntity() = ImageSearchResponse(
        searchImages = searchImages.map { it.asEntity() },
        meta = meta.asEntity()
    )
}

