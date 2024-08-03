package com.seosh817.kakaoimagesearch.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seosh817.kakaoimagesearch.core.data.local.dao.BookmarkDao
import com.seosh817.kakaoimagesearch.core.data.local.model.BookmarkEntity

@Database(
    entities = [
        BookmarkEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class KakaoImageSearchDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        const val DATABASE_NAME = "kakao_image_search.db"
    }
}