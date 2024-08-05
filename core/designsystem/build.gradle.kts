plugins {
    alias(libs.plugins.kakaoimagesearch.android.library)
    alias(libs.plugins.kakaoimagesearch.android.library.compose)
}

android {
    namespace = "com.seosh817.kakaoimagesearch.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}