package com.seosh817.kakaoimagesearch.plugins

import com.seosh817.kakaoimagesearch.configurations.configureHiltKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class KotlinHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        configureHiltKotlin()
    }
}
