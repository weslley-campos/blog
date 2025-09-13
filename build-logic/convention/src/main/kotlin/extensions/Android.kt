package extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configure Android common settings. to allow enable compose preview in commonMain, because
 * compose preview ui tooling relies on android sdk to render previews.
 */
fun Project.configureAndroid(
    extension: CommonExtension<*, *, *, *, *, *>,
) {
    val nameSpace = when (extension) {
        is ApplicationExtension -> blogPackage
        else -> packageName
    }
    extension.apply {
        // Required to enable modularization in multiplatform project
        // The Android configuration is REQUIRED for KSP code generation to work properly.
        // compilation targets, and KSP tasks like kspCommonMainKotlinMetadata won't be available.
        // This enables Koin annotation processing to generate modules in build/generated/
        namespace = nameSpace
        compileSdk = libs.versions.compileSdk.get().toInt()
        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }

    // Disable Android test execution tasks as they are only needed for preview support
    // Keep lint and other build-related test tasks enabled
    afterEvaluate {
        tasks.matching { task ->
            task.name.matches(Regex("test(Debug|Release)UnitTest"))
        }.configureEach {
            enabled = false
        }
    }
}

internal fun Project.configureAndroidTarget(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        androidTarget {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
}
