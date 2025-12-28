plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader

    // Enable compose preview in AS Narwhal
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Kotlin
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    // Compose
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose.hot.reload) apply false

    // KSP
    alias(libs.plugins.ksp) apply false

    // Detekt
    alias(libs.plugins.detekt) apply false
}
