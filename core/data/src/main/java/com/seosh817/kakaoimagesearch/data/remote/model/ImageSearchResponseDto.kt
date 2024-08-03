package com.seosh817.kakaoimagesearch.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageSearchResponseDto (

    @SerialName("documents")
    var documents : List<DocumentDTO>,
    @SerialName("meta")
    var meta : MetaDto
)
