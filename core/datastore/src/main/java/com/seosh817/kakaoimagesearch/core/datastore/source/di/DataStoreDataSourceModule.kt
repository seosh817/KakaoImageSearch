package com.seosh817.kakaoimagesearch.core.datastore.source.di

import com.seosh817.kakaoimagesearch.core.datastore.source.AppSettingsDataSource
import com.seosh817.kakaoimagesearch.core.datastore.source.AppSettingsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreDataSourceModule {

    @Singleton
    @Binds
    fun bindSettingsDataSource(appSettingsDataSourceImpl: AppSettingsDataSourceImpl): AppSettingsDataSource
}