plugins {
    alias(libs.plugins.happtech.android.application)
    alias(libs.plugins.happtech.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.github.mcgalanes.happtech"

    defaultConfig {
        applicationId = "com.github.mcgalanes.happtech"
    }
}

dependencies {
    implementation(projects.core.design)
    implementation(projects.core.data)
    implementation(projects.core.navigation)
    implementation(projects.core.network)

    // Features
    implementation(projects.feature.museumcollection.list)
    implementation(projects.feature.museumcollection.detail)

    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Arch Components
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)


    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
