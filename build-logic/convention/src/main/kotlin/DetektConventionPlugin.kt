import extensions.detektPlugins
import extensions.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.detekt.get().pluginId)

            extensions.configure<DetektExtension> {
                buildUponDefaultConfig = true
                toolVersion = libs.versions.detekt.get()
                config.setFrom(files("$rootDir/detekt.yml"))
                parallel = true
                autoCorrect = true
                source.setFrom(
                    "src/commonMain/kotlin",
                    "src/jsMain/kotlin",
                    "src/wasmJsMain/kotlin",
                    "src/main/kotlin",
                    "src/test/kotlin",
                    "src/commonTest/kotlin",
                    "src/jsTest/kotlin"
                )
            }

            tasks.withType<Detekt>().configureEach {
                exclude(
                    "**/.gradle/**",
                    "**/.idea/**",
                    "**/build/**",
                    ".github/**",
                    "gradle/**",
                )
                reports {
                    html.required.set(true)
                }
            }

            dependencies {
                detektPlugins(libs.detekt.formatting)
            }
        }
    }
}
