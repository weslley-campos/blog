package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinLibrary(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        jvm()

        sourceSets.apply {
            commonMain.dependencies {
                implementation(libs.kotlin.collections.imutable)
            }
        }
    }
    configureKotlin<KotlinMultiplatformExtension>()
}

inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() = configure<T> {
    val warningsAsErrors = providers.gradleProperty("warningsAsErrors").map {
        it.toBoolean()
    }.orElse(false)

    when (this) {
        is KotlinMultiplatformExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions.also { it.jvmTarget.set(JvmTarget.JVM_17) }
        is KotlinAndroidProjectExtension -> compilerOptions.also { it.jvmTarget.set(JvmTarget.JVM_17) }
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        allWarningsAsErrors.assign(warningsAsErrors)
        freeCompilerArgs.addAll(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xexpect-actual-classes",
            "-Xexplicit-backing-fields"
        )
    }
}
