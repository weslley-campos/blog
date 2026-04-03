plugins {
    alias(libs.plugins.blog.library)
    alias(libs.plugins.blog.koin)
    alias(libs.plugins.blog.detekt)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.datastore.core.preferences)
        }
        wasmJsMain.dependencies {
            implementation(libs.datastore.core.okio)
        }
    }
}
