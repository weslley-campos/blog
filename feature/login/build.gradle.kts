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

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
        }
    }
}
