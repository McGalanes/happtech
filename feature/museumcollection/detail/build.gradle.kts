plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.happtech.android.compose)
}

android {
    namespace = "com.github.mcgalanes.happtech.feature.museumcollection.detail"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.design)
    implementation(projects.core.navigation)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.bundles.coil)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.koin.androidx.compose)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
