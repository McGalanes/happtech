import com.android.build.gradle.LibraryExtension
import com.github.mcgalanes.happtech.modularization.configureAndroid
import com.github.mcgalanes.happtech.modularization.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
            }

            dependencies {
                val koinBom = libs.findLibrary("koin-bom").get()
                "implementation"(platform(koinBom))
                "implementation"(libs.findLibrary("koin-core").get())
                "implementation"(libs.findLibrary("koin-android").get())
            }
        }
    }
}
