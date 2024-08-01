import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "kakaoimagesearch.android.application"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "kakaoimagesearch.android.application.compose"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "kakaoimagesearch.android.library"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "kakaoimagesearch.android.library.compose"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidLibraryComposeConventionPlugin"
        }
        register("androidRoom") {
            id = "kakaoimagesearch.android.room"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidRoomConventionPlugin"
        }
        register("androidHilt") {
            id = "kakaoimagesearch.android.hilt"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidHiltConventionPlugin"
        }
        register("kotlinHilt") {
            id = "kakaoimagesearch.kotlin.hilt"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.KotlinHiltConventionPlugin"
        }
        register("kotlinJvm") {
            id = "kakaoimagesearch.kotlin.jvm.library"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.KotlinJvmLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "kakaoimagesearch.android.feature"
            implementationClass = "com.seosh817.kakaoimagesearch.plugins.AndroidFeatureConventionPlugin"
        }
    }
}
