import extensions.configureDesktop
import extensions.configureKotlin
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Convention plugin for the JVM/Desktop application entry point module (`jvmApp`).
 */
class JvmApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.asProvider().get().pluginId)
        apply(plugin = libs.plugins.compose.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.compose.compiler.get().pluginId)
        apply(plugin = libs.plugins.compose.hot.reload.get().pluginId)

        val composeDeps = extensions.getByType<ComposeExtension>().dependencies

        configureKotlin<KotlinMultiplatformExtension>()
        extensions.configure<KotlinMultiplatformExtension> {
            jvm {
                compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
            }

            sourceSets.commonMain.dependencies {
                implementation(project(":app"))
            }
            sourceSets.jvmMain.dependencies {
                implementation(composeDeps.desktop.currentOs)
                implementation(libs.kotlin.coroutines.swing)
            }
        }

        configureDesktop()
    }
}
