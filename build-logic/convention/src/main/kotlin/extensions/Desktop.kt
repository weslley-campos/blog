package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

fun Project.configureDesktop() {
    val compose = extensions.getByType<ComposeExtension>()
    compose.extensions.configure<DesktopExtension> {
        application {
            mainClass = "br.com.weslleycampos.blog.MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "br.com.weslleycampos.blog"
                packageVersion = "1.0.0"
            }
        }
    }
}