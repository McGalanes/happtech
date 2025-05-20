plugins {
    alias(libs.plugins.happtech.android.library)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.testing"
}

dependencies {
    implementation(libs.androidx.test.runner)
    implementation(libs.kotlinx.coroutines.test)
}
