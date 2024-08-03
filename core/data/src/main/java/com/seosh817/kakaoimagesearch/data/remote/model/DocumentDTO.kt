package com.seosh817.kakaoimagesearch.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocumentDTO (

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
