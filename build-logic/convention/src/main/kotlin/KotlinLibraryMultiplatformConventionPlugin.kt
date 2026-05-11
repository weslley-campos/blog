import extensions.configureAndroidTarget
import extensions.configureKotlinLibrary
import extensions.configureWasmJsLibrary
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin that configures a Kotlin Multiplatform library module
 * targeting Android (via `com.android.kotlin.multiplatform.library`), JVM,
 * and Wasm/JS.
 */
@OptIn(ExperimentalWasmDsl::class)
class KotlinLibraryMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.asProvider().get().pluginId)
        apply(plugin = libs.plugins.kotlin.multiplatform.library.get().pluginId)
        apply(plugin = libs.plugins.kotlin.serialization.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension>(::configureKotlinLibrary)
        extensions.configure<KotlinMultiplatformExtension>(::configureAndroidTarget)
        extensions.configure<KotlinMultiplatformExtension>(::configureWasmJsLibrary)
    }
}
