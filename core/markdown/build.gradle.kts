plugins {
    alias(libs.plugins.blog.library)
    alias(libs.plugins.blog.compose.library)
    alias(libs.plugins.blog.koin)
    alias(libs.plugins.blog.detekt)
}

kotlin {
    sourceSets.apply {
        commonMain.dependencies {
            implementation(libs.jetbrains.markdown)
            implementation(projects.core.ui)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
