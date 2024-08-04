plugins {
    alias(libs.plugins.kakaoimagesearch.android.library)
    alias(libs.plugins.kakaoimagesearch.android.hilt)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.seosh817.kakaoimagesearch.core.datastore"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.domain)

    implementation(libs.androidx.dataStore.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.protobuf.kotlin.lite)
}
