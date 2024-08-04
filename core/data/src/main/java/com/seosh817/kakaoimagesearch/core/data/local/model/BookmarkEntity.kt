package com.seosh817.kakaoimagesearch.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BookmarkEntity.TABLE_NAME)
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_IMAGE_URL)
    val imageUrl: String,
    @ColumnInfo(name = COLUMN_COLLECTION)
    val collection: String,
    @ColumnInfo(name = COLUMN_DATETIME)
    val datetime: String,
    @ColumnInfo(name = COLUMN_DISPLAY_SITE_NAME)
    val displaySiteName: String,
    @ColumnInfo(name = COLUMN_DOC_URL)
    val docUrl: String,
    @ColumnInfo(name = COLUMN_THUMBNAIL_URL)
    val thumbnailUrl: String,
    @ColumnInfo(name = COLUMN_WIDTH)
    val width: Int,
    @ColumnInfo(name = COLUMN_HEIGHT)
    val height: Int,
    @ColumnInfo(name = COLUMN_QUERY)
    val query: String
) {
    companion object {
        const val TABLE_NAME = "bookmark_table"
        const val COLUMN_COLLECTION = "collection"
        const val COLUMN_DATETIME = "datetime"
        const val COLUMN_DISPLAY_SITE_NAME = "display_site_name"
        const val COLUMN_DOC_URL = "doc_url"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_THUMBNAIL_URL = "thumbnail_url"
        const val COLUMN_WIDTH = "width"
        const val COLUMN_HEIGHT = "height"
        const val COLUMN_QUERY = "query"
    }
}
