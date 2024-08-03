package com.seosh817.kakaoimagesearch.data.remote.service.di

import com.seosh817.kakaoimagesearch.data.remote.service.search.ImageSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideKakaoImageSearchService(
        retrofit: Retrofit
    ): ImageSearchService = retrofit.create(ImageSearchService::class.java)
}
