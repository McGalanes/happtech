plugins {
    alias(libs.plugins.happtech.android.library)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.data"
}

dependencies {
    implementation(projects.happtech.core.domain)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
