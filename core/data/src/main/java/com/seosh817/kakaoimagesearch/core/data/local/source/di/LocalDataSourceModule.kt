package com.seosh817.kakaoimagesearch.core.data.local.source.di

import com.seosh817.kakaoimagesearch.core.data.local.source.BookmarkLocalDataSource
import com.seosh817.kakaoimagesearch.core.data.local.source.impl.BookmarkLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Singleton
    @Binds
    fun bindBookmarkLocalDataSource(bookmarkLocalDataSourceImpl: BookmarkLocalDataSourceImpl): BookmarkLocalDataSource
}