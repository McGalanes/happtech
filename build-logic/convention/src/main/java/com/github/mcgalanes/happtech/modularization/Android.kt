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
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        buildFeatures.buildConfig = false
    }
}
