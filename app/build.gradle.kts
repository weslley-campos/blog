plugins {
    alias(libs.plugins.blog.library)
    alias(libs.plugins.blog.compose.library)
    alias(libs.plugins.blog.koin)
    alias(libs.plugins.blog.detekt)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            api(projects.core.navigation)
            implementation(projects.core.ui)
            implementation(projects.feature.home)
            implementation(projects.feature.about)
        }
    }
}
