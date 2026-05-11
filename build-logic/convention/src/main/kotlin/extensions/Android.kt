package extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configure Android common settings. to allow enable compose preview in commonMain, because
 * compose preview ui tooling relies on android sdk to render previews.
 */
fun Project.configureAndroid(extension: CommonExtension) {
    val nameSpace = when (extension) {
        is ApplicationExtension -> blogPackage
        else -> packageName
    }
    extension.apply {
        namespace = nameSpace
        compileSdk = libs.versions.compileSdk.get().toInt()
        defaultConfig.apply {
            minSdk = libs.versions.minSdk.get().toInt()
        }
        compileOptions.apply {
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
    extension: KotlinMultiplatformExtension,
) {
    extension.extensions.configure<KotlinMultiplatformAndroidLibraryExtension> {
        namespace = packageName
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
    }
}
