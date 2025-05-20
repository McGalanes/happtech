plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.happtech.android.compose)
}

android {
    namespace = "com.github.mcgalanes.happtech.feature.museumcollection.list"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.design)

    // Core Android dependencies
    implementation(libs.androidx.activity.compose)

    // Arch Components
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.compose.material.icons)
    implementation(libs.kotlinx.collections.immutable)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(projects.core.testing)
}
