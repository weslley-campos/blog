import com.android.build.api.dsl.ApplicationExtension
import extensions.configureAndroid
import extensions.configureAndroidTarget
import extensions.configureDesktop
import extensions.configureKotlin
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
@Suppress("MagicNumber")
class KotlinApplicationMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.android.application.get().pluginId)
        apply(plugin = libs.plugins.kotlin.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.kotlin.serialization.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension>(::configureKotlin)
        extensions.configure<ApplicationExtension>(::configureAndroid)
        extensions.configure<KotlinMultiplatformExtension>(::configureAndroidTarget)
        extensions.configure<KotlinMultiplatformExtension>(::configureWasmJs)
    }
}
