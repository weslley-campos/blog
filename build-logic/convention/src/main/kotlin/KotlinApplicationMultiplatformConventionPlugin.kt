import extensions.configureAndroidLibrary
import extensions.configureWasmJs
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin for configuring Kotlin Multiplatform applications.
 *
 * Applies kotlin.library.multiplatform plugin to enable KSP commonMain code generation
 * and configures Android library target for proper multiplatform compilation.
 *
 * @since 1.0.0
 */
class KotlinApplicationMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.kotlin.library.multiplatform.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension>(::configureWasmJs)
        extensions.configure<KotlinMultiplatformExtension>(::configureAndroidLibrary)
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project(":core:ui"))
                    implementation(project(":core:common"))
                }
            }
        }
    }
}
