plugins {
    alias(libs.plugins.kakaoimagesearch.android.feature)
    alias(libs.plugins.kakaoimagesearch.android.library.compose)
}

android {
    namespace = "com.seosh817.kakaoimagesearch.bookmarks"
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}