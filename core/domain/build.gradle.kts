plugins {
    alias(libs.plugins.happtech.android.library)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.domain"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
