package com.seosh817.kakaoimagesearch.core.datastore.source

import androidx.datastore.core.DataStore
import com.seosh817.kakaoimagesearch.core.datastore.AppLanguageProto
import com.seosh817.kakaoimagesearch.core.datastore.DarkThemeModeProto
import com.seosh817.kakaoimagesearch.core.datastore.UserPreferences
import com.seosh817.kakaoimagesearch.core.datastore.copy
import com.seosh817.kakaoimagesearch.domain.entity.AppLanguage
import com.seosh817.kakaoimagesearch.domain.entity.AppSettings
import com.seosh817.kakaoimagesearch.domain.entity.DarkThemeMode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppSettingsDataSourceImpl @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>,
) : AppSettingsDataSource {

    override val appSettings = userPreferencesDataStore.data
        .map {
            AppSettings(
                darkThemeMode = when (it.darkThemeMode) {
                    DarkThemeModeProto.DARK_THEME_MODE_FOLLOW_SYSTEM -> DarkThemeMode.FOLLOW_SYSTEM
                    DarkThemeModeProto.DARK_THEME_MODE_LIGHT -> DarkThemeMode.LIGHT
                    null,
                    DarkThemeModeProto.DARK_THEME_MODE_UNSPECIFIED,
                    DarkThemeModeProto.UNRECOGNIZED,
                    DarkThemeModeProto.DARK_THEME_MODE_DARK -> DarkThemeMode.DARK
                },
                appLanguage = when (it.appLanguage) {
                    AppLanguageProto.APP_LANGUAGE_KOREAN -> AppLanguage.KOREAN
                    AppLanguageProto.APP_LANGUAGE_ENGLISH -> AppLanguage.ENGLISH
                    else -> AppLanguage.DEFAULT
                },
            )
        }

    override suspend fun setDarkThemeMode(darkThemeMode: DarkThemeMode) {
        userPreferencesDataStore.updateData {
            it.copy {
                this.darkThemeMode = when (darkThemeMode) {
                    DarkThemeMode.LIGHT -> DarkThemeModeProto.DARK_THEME_MODE_LIGHT
                    DarkThemeMode.DARK -> DarkThemeModeProto.DARK_THEME_MODE_DARK
                    else -> DarkThemeModeProto.DARK_THEME_MODE_FOLLOW_SYSTEM
                }
            }
        }
    }

    override suspend fun setAppLanguage(appLanguage: AppLanguage) {
        userPreferencesDataStore.updateData {
            it.copy {
                this.appLanguage = when (appLanguage) {
                    AppLanguage.DEFAULT -> AppLanguageProto.APP_LANGUAGE_DEFAULT
                    AppLanguage.ENGLISH -> AppLanguageProto.APP_LANGUAGE_ENGLISH
                    AppLanguage.KOREAN -> AppLanguageProto.APP_LANGUAGE_KOREAN
                }
            }
        }
    }
}
