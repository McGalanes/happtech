plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.happtech.android.room)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.database"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
