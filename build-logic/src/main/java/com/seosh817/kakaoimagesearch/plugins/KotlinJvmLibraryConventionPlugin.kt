package com.seosh817.kakaoimagesearch.plugins

import com.seosh817.kakaoimagesearch.configurations.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class KotlinJvmLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with (pluginManager) {
            apply("org.jetbrains.kotlin.jvm")
        }
        configureKotlinJvm()
    }
}
