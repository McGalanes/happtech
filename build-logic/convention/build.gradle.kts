plugins {
    `kotlin-dsl`
}

group = "com.github.mcgalanes.happtech.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("happtechAndroidApplication") {
            id = "happtech.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("happtechAndroidLibrary") {
            id = "happtech.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("happtechAndroidRoom") {
            id = "happtech.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("happtechAndroidCompose") {
            id = "happtech.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}
