package com.github.mcgalanes.happtech.modularization

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

internal fun configureAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 36

        defaultConfig {
            minSdk = 30
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_22
            targetCompatibility = JavaVersion.VERSION_22
        }

        buildFeatures.buildConfig = false
    }
}
