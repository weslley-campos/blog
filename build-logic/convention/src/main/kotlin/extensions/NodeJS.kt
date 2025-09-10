package extensions

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalWasmDsl::class)
fun Project.configureNodeJSLibrary(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        // Configure WasmJS target for web platform support
        // Uses Node.js runtime for headless execution environment
        wasmJs {
            nodejs()
        }
    }
}
