package com.seosh817.kakaoimagesearch.data.remote.source.di

import com.seosh817.kakaoimagesearch.data.remote.source.ImageSearchRemoteDataSource
import com.seosh817.kakaoimagesearch.data.remote.source.impl.ImageSearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ImageSearchDataSourceModule {

    @Singleton
    @Binds
    fun bindMoviesDataSource(imageSearchSourceImpl: ImageSearchRemoteDataSourceImpl): ImageSearchRemoteDataSource
}
