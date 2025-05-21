plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.navigation"
}

dependencies {
    implementation(libs.ktor.serialization)
}
