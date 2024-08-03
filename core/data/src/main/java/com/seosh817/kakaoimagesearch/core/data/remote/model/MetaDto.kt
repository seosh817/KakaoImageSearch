package com.seosh817.kakaoimagesearch.core.data.remote.model

import com.seosh817.kakaoimagesearch.domain.entity.Meta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto (

   @SerialName("is_end")
   var isEnd : Boolean,
   @SerialName("pageable_count")
   var pageableCount : Int,
   @SerialName("total_count")
   var totalCount : Int
) {
    fun asEntity() = Meta(
        isEnd = isEnd,
        pageableCount = pageableCount,
        totalCount = totalCount
    )
}
