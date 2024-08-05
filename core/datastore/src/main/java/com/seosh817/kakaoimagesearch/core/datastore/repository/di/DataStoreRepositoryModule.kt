package com.seosh817.kakaoimagesearch.core.datastore.repository.di

import com.seosh817.kakaoimagesearch.core.datastore.repository.AppSettingsRepositoryImpl
import com.seosh817.kakaoimagesearch.domain.repository.AppSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreRepositoryModule {

    @Singleton
    @Binds
    fun bindAppSettingsRepository(appSettingsRepositoryImpl: AppSettingsRepositoryImpl): AppSettingsRepository
}