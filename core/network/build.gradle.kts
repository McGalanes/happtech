plugins {
    alias(libs.plugins.happtech.android.library)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.network"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.bundles.ktor)
}
