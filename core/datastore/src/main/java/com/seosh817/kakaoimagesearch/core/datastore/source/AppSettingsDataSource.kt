package com.seosh817.kakaoimagesearch.core.datastore.source

import com.seosh817.kakaoimagesearch.domain.entity.AppLanguage
import com.seosh817.kakaoimagesearch.domain.entity.AppSettings
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode
import kotlinx.coroutines.flow.Flow

interface AppSettingsDataSource {

    val appSettings: Flow<AppSettings>

    suspend fun setDarkThemeMode(darkThemeMode: DarkThemeMode)

    suspend fun setAppLanguage(appLanguage: AppLanguage)
}