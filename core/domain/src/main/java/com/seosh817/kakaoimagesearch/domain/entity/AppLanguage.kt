package com.seosh817.kakaoimagesearch.domain.entity

enum class AppLanguage(val locale: String) {
    DEFAULT(""),
    KOREAN("ko"),
    ENGLISH("en");

    companion object {
        fun fromLocale(locale: String): AppLanguage {
            return entries.firstOrNull { it.locale == locale } ?: DEFAULT
        }
    }
}