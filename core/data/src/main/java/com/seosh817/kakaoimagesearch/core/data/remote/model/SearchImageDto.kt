package com.seosh817.kakaoimagesearch.core.data.remote.model

import com.seosh817.kakaoimagesearch.domain.entity.SearchImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchImageDto (

   @SerialName("collection")
   var collection : String,
   @SerialName("datetime")
   var datetime : String,
   @SerialName("display_sitename")
   var displaySitename : String,
   @SerialName("doc_url")
   var docUrl : String,
   @SerialName("height")
   var height : Int,
   @SerialName("image_url")
   var imageUrl : String,
   @SerialName("thumbnail_url")
   var thumbnailUrl : String,
   @SerialName("width")
   var width : Int
)
