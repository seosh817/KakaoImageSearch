plugins {
    alias(libs.plugins.kakaoimagesearch.android.library)
    alias(libs.plugins.kakaoimagesearch.kotlin.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.seosh817.kakaoimagesearch.core.domain"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)
    // alternatively - without Android dependencies for tests
    implementation(libs.androidx.paging.common)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
