package extensions

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlin(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        jvm()

        compilerOptions {
            // Common compiler options applied to all Kotlin source sets
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }
}
