plugins {
    alias(libs.plugins.happtech.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.github.mcgalanes.happtech.core.data"
}

dependencies {
    implementation(projects.happtech.core.domain)
    implementation(projects.happtech.core.network)
    implementation(projects.happtech.core.database)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.ktor.serialization)
    implementation(libs.ktor.client.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.ktor.client.mock)
}
