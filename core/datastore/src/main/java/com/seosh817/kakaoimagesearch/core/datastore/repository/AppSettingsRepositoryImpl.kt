package com.seosh817.kakaoimagesearch.core.datastore.repository

import com.seosh817.kakaoimagesearch.core.datastore.source.AppSettingsDataSource
import com.seosh817.kakaoimagesearch.domain.entity.AppLanguage
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode
import com.seosh817.kakaoimagesearch.domain.repository.AppSettingsRepository
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val appSettingsDataSource: AppSettingsDataSource
) : AppSettingsRepository {

    override val appSettings = appSettingsDataSource.appSettings

    override suspend fun setDarkThemeMode(darkThemeMode: DarkThemeMode) {
        appSettingsDataSource.setDarkThemeMode(darkThemeMode)
    }

    override suspend fun setAppLanguage(appLanguage: AppLanguage) {
        appSettingsDataSource.setAppLanguage(appLanguage)
    }
}