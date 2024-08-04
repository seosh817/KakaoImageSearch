package com.seosh817.kakaoimagesearch.plugins

import com.google.devtools.ksp.gradle.KspExtension
import com.seosh817.kakaoimagesearch.configurations.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.process.CommandLineArgumentProvider
import java.io.File

class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {

            }

            dependencies {
                add("api", libs.findLibrary("room.runtime").get())
                add("api", libs.findLibrary("room.ktx").get())
                add("api", libs.findLibrary("room.paging").get())
                add("ksp", libs.findLibrary("room.compiler").get())
            }
        }
    }
}