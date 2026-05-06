plugins {
    alias(libs.plugins.blog.library)
    alias(libs.plugins.blog.compose.library)
    alias(libs.plugins.blog.koin)
    alias(libs.plugins.blog.detekt)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.navigation)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
        }
    }
}
