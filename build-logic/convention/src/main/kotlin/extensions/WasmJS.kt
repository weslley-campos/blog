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
                    outputFileName = "composeApp.js"
                    devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                        static = (static ?: mutableListOf()).apply {
                            // Serve sources to debug inside browser
                            add(rootDirPath)
                            add(projectDirPath)
                        }
                        port = serverPort
                    }
                }
                testTask {
                    useKarma {
                        useChromiumHeadless()
                    }
                }
            }
            binaries.executable()
        }
    }
}
