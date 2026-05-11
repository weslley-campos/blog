import com.android.build.api.dsl.ApplicationExtension
import extensions.configureAndroid
import extensions.configureKotlin
import extensions.implementation
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Convention plugin for the Android application entry point module (`androidApp`).
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.android.application.get().pluginId)

        extensions.configure<ApplicationExtension>(::configureAndroid)
        configureKotlin<KotlinAndroidProjectExtension>()

        dependencies {
            implementation(project(":app"))
            implementation(libs.activity.ktx)
        }
    }
}
