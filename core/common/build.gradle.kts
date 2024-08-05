plugins {
    alias(libs.plugins.kakaoimagesearch.android.library)
    alias(libs.plugins.kakaoimagesearch.android.hilt)
}

android {
    namespace = "com.seosh817.kakaoimagesearch.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}