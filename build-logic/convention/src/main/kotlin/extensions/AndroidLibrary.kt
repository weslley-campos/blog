package extensions

import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("MagicNumber", "UnstableApiUsage")
fun Project.configureAndroidLibrary(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        // Required to enable modularization in multiplatform project
        // The Android library configuration is REQUIRED for KSP code generation to work properly.
        // Without this, the kotlin.library.multiplatform plugin won't create the necessary
        // compilation targets, and KSP tasks like kspCommonMainKotlinMetadata won't be available.
        // This enables Koin annotation processing to generate modules in build/generated/
        androidLibrary {
            namespace = packageName
            compileSdk = 36
        }
    }
}
