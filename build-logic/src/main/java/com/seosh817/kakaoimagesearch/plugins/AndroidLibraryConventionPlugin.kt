package com.seosh817.kakaoimagesearch.plugins

import com.seosh817.kakaoimagesearch.configurations.configureAndroidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureAndroidLibrary()
        }
    }
}
