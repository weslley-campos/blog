import com.google.devtools.ksp.gradle.KspExtension
import extensions.kspCommonMainMetadata
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin for setting up Koin dependency injection in Kotlin Multiplatform projects.
 *
 * This plugin provides a complete, opinionated setup for using Koin with annotation-based
 * dependency injection in multiplatform projects. It automatically configures KSP (Kotlin
 * Symbol Processing) for code generation and ensures proper task dependencies.
 *
 * ## Usage
 * Apply this plugin in your module's `build.gradle.kts`:
 * ```kotlin
 * plugins {
 *     alias(libs.plugins.blog.koin)
 * }
 * ```
 *
 * ## Task Dependency Resolution
 *
 * This plugin solves a common KMP + KSP issue where compilation tasks run before KSP
 * code generation completes, causing build failures. It uses `tasks.configureEach`
 * to dynamically configure dependencies as tasks are created.
 *
 * @since 1.0.0
 *
 * @see [Koin Documentation](https://insert-koin.io/docs/reference/koin-annotations/start)
 * @see [KSP Documentation](https://kotlinlang.org/docs/ksp-overview.html)
 */
class KoinMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.asProvider().get().pluginId)
        apply(plugin = libs.plugins.ksp.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain.dependencies {
                    implementation(project.dependencies.platform(libs.koin.bom))
                    implementation(libs.koin.core)
                    api(libs.koin.annotations)

                    implementation(libs.koin.compose)
                    implementation(libs.koin.compose.viewmodel)
                    implementation(libs.koin.compose.viewmodel.navigation)
                }
            }
            // KSP Common sourceSet
            sourceSets.named("commonMain").configure {
                kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }
        }

        extensions.configure<KspExtension> {
            arg("KOIN_CONFIG_CHECK", "true")
            arg("KOIN_LOG_TIMES", "true")
        }

        // KSP Tasks
        dependencies {
            kspCommonMainMetadata(libs.koin.compiler)
        }

        // Trigger Common Metadata Generation from Native tasks. The `kspCommonMainKotlinMetadata`
        // task only exists on multi-target KMP modules with KSP processors registered on commonMain
        // metadata. Single-target KMP modules (e.g., `wasmApp`, `jvmApp`) skip metadata compilation,
        // so we wire the dependency through a TaskCollection that's a no-op when empty.
        val kspCommonMainTask = tasks.matching { it.name == "kspCommonMainKotlinMetadata" }
        tasks.matching { it.name.startsWith("ksp") && it.name != "kspCommonMainKotlinMetadata" }.configureEach {
            dependsOn(kspCommonMainTask)
        }
    }
}
