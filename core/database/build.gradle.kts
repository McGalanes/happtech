plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.happtech.android.room)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.database"
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
