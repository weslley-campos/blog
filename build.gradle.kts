plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.library.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false

    // KSP
    alias(libs.plugins.ksp) apply false

    // Detekt
    alias(libs.plugins.detekt) apply false
}
