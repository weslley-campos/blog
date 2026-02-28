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
            freeCompilerArgs.addAll(
                "-Xexpect-actual-classes",
                "-Xexplicit-backing-fields",
                "-Xenable-suspend-function-exporting",
                "-Xdata-flow-based-exhaustiveness"
            )
        }

        sourceSets.apply {
            commonMain.dependencies {
                implementation(libs.kotlin.collections.imutable)
            }
        }
    }
}
