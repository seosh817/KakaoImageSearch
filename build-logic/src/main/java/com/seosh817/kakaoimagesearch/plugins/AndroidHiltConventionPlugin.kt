package com.seosh817.kakaoimagesearch.plugins

import com.seosh817.kakaoimagesearch.configurations.configureHiltAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        configureHiltAndroid()
    }
}
