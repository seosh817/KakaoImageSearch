package com.seosh817.kakaoimagesearch.data.remote.model

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
)
