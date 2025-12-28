import extensions.blogPackage
import extensions.configureComposeMultiplatform
import extensions.configureComposeResources
import extensions.configureDesktop
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeApplicationMultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply(plugin = libs.plugins.kotlin.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.compose.multiplatform.get().pluginId)
        apply(plugin = libs.plugins.compose.compiler.get().pluginId)

        extensions.configure<KotlinMultiplatformExtension>(::configureComposeMultiplatform)
        configureComposeResources("$blogPackage.resources")
        configureDesktop()
    }
}
