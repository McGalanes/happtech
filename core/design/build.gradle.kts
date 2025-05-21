plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.happtech.android.compose)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.design"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.appcompanist.systemuicontroller)
}
