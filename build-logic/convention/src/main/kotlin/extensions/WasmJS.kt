package extensions

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

@OptIn(ExperimentalWasmDsl::class)
fun Project.configureWasmJs(
    extension: KotlinMultiplatformExtension
) {
    val serverPort = 5001
    extension.apply {
        wasmJs {
            browser {
                val rootDirPath = project.rootDir.path
                val projectDirPath = project.projectDir.path
                commonWebpackConfig {
                    outputFileName = "wasmApp.js"
                    devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                        static(rootDirPath)
                        static(projectDirPath)
                        port = serverPort
                    }
                }
                testTask { enabled = false }
            }
            binaries.executable()
        }
    }
}

@OptIn(ExperimentalWasmDsl::class)
fun Project.configureWasmJsLibrary(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        wasmJs {
            browser {
                testTask { enabled = false }
            }
            binaries.library()
        }
    }
}