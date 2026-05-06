plugins {
    alias(libs.plugins.blog.application)
    alias(libs.plugins.blog.compose.application)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.blog.koin)
    alias(libs.plugins.blog.detekt)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.common)
            implementation(projects.core.navigation)
            implementation(projects.feature.home)
            implementation(projects.feature.about)
            implementation(projects.feature.login)
        }
    }
}

