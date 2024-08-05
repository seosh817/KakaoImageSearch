package com.seosh817.kakaoimagesearch.util

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.seosh817.kakaoimagesearch.domain.entity.AppLanguage

object LanguageUtil {

    fun setLanguage(context: Context, appLanguage: AppLanguage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            if (appLanguage == AppLanguage.DEFAULT) {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.getDefault()
            } else {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags(appLanguage.locale)
            }
        else {
            if (appLanguage == AppLanguage.DEFAULT)
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(appLanguage.locale))
            else
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.getDefault())
        }
    }
}