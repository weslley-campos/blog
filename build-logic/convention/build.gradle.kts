import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "br.com.weslleycampos.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kotlinApplicationMultiplatform") {
            id = libs.plugins.blog.application.get().pluginId
            implementationClass = "KotlinApplicationMultiplatformConventionPlugin"
        }

        register("kotlinLibraryMultiplatform") {
            id = libs.plugins.blog.library.get().pluginId
            implementationClass = "KotlinLibraryMultiplatformConventionPlugin"
        }

        register("composeApplicationMultiplatform") {
            id = libs.plugins.blog.compose.application.get().pluginId
            implementationClass = "ComposeApplicationMultiplatformConventionPlugin"
        }

        register("composeLibraryMultiplatform") {
            id = libs.plugins.blog.compose.library.get().pluginId
            implementationClass = "ComposeLibraryMultiplatformConventionPlugin"
        }

        register("koinMultiplatform") {
            id = libs.plugins.blog.koin.get().pluginId
            implementationClass = "KoinMultiplatformConventionPlugin"
        }

        register("detektMutiplaform") {
            id = libs.plugins.blog.detekt.get().pluginId
            implementationClass = "DetektConventionPlugin"
        }
    }
}
