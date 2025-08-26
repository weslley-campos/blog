import com.android.build.api.dsl.androidLibrary
import extensions.libs
import extensions.packageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

/**
 * Convention plugin that configures a Kotlin Multiplatform module.
 */
@Suppress("MagicNumber", "UnstableApiUsage")
@OptIn(ExperimentalWasmDsl::class)
class KotlinLibraryMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.kotlin.library.multiplatform.get().pluginId)

        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
            // Required to enable modularization in multiplatform project
            androidLibrary {
                namespace = packageName
                compileSdk = 36
            }

            // Configure WasmJS target for web platform support
            // Uses Node.js runtime for headless execution environment
            wasmJs {
                nodejs()
            }
        }
    }
}
