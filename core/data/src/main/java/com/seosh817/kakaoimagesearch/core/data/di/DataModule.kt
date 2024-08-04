package com.seosh817.kakaoimagesearch.core.data.di

import com.seosh817.kakaoimagesearch.core.data.repository.BookmarkRepositoryImpl
import com.seosh817.kakaoimagesearch.core.data.repository.ImageSearchRepositoryImpl
import com.seosh817.kakaoimagesearch.domain.repository.BookmarkRepository
import com.seosh817.kakaoimagesearch.domain.repository.ImageSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindImageSearchRepository(imageSearchRepositoryImpl: ImageSearchRepositoryImpl): ImageSearchRepository

    @Singleton
    @Binds
    fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl): BookmarkRepository
}
