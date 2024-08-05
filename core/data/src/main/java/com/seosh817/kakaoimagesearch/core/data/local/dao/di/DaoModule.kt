package com.seosh817.kakaoimagesearch.core.data.local.dao.di

import com.seosh817.kakaoimagesearch.core.data.local.KakaoImageSearchDatabase
import com.seosh817.kakaoimagesearch.core.data.local.dao.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun providesMovieDao(
        database: KakaoImageSearchDatabase,
    ): BookmarkDao = database.bookmarkDao()
}