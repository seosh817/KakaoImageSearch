plugins {
    alias(libs.plugins.kakaoimagesearch.android.feature)
    alias(libs.plugins.kakaoimagesearch.android.library.compose)
}

android {
    namespace = "com.seosh817.kakaoimagesearch.feature.search"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)
    implementation(projects.core.data)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}