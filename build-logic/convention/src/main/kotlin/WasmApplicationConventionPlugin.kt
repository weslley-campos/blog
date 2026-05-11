import extensions.configureKotlin
import extensions.configureWasmJs
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin for the Wasm/JS browser application entry point module (`wasmApp`).
 *
 * Applies Kotlin Multiplatform with only the `wasmJs` target plus Compose
 * Multiplatform. Wires the standard entry-point dependencies (`:app` for
 * shared composables/DI, `nav3-browser` for browser history integration).
 * The browser dev server runs on port 5001 and tests are disabled.
 */
@OptIn(ExperimentalWasmDsl::class)
class WasmApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.asProvider().get().pluginId)
        apply(plugin = libs.plugins.compose.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.compose.compiler.get().pluginId)

        configureKotlin<KotlinMultiplatformExtension>()
        extensions.configure<KotlinMultiplatformExtension>(::configureWasmJs)
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonMain.dependencies {
                implementation(project(":app"))
            }
            sourceSets.wasmJsMain.dependencies {
                implementation(libs.nav3.browser)
            }
        }
    }
}
