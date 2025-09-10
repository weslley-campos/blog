import extensions.configureAndroidLibrary
import extensions.configureNodeJSLibrary
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin that configures a Kotlin Multiplatform module.
 */
@OptIn(ExperimentalWasmDsl::class)
class KotlinLibraryMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.kotlin.library.multiplatform.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension>(::configureAndroidLibrary)
        extensions.configure<KotlinMultiplatformExtension>(::configureNodeJSLibrary)
    }
}
