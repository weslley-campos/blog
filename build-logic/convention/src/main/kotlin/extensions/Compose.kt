package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeMultiplatform(
    extension: KotlinMultiplatformExtension
) {
    val compose = extensions.getByType<ComposeExtension>().dependencies

    extension.apply {
        sourceSets.apply {
            commonMain.dependencies {
                // Compose Runtime (core)
                // Core, platform-agnostic runtime for Compose: provides the reactive state model,
                // recomposition engine, and effect APIs (e.g., `remember`, `mutableStateOf`,
                // `derivedStateOf`, `LaunchedEffect`, snapshots).
                implementation(libs.compose.runtime)

                // Compose UI Graphics
                // Provides fundamental graphics primitives and operations for drawing in Compose.
                // Includes things like `Color`, `Brush`, `ImageBitmap`, `Canvas`, and other low-level drawing APIs.
                implementation(libs.compose.ui)

                // Compose UI Utilities
                // Provides utility functions and helpers for Compose UI that complement core functionality.
                // Includes layout helpers, geometry utilities, convenience functions, and performance tools
                // for building more complex UI components and handling advanced use cases.
                implementation(libs.compose.ui.util)

                // Compose UI Tooling Preview
                // Provides preview functionality for Compose UI components, allowing developers to
                // see how their composables look without running the full application. Essential
                // for development-time previews and design iteration.
                implementation(libs.compose.ui.tooling.preview)

                // Compose Foundation
                // Provides fundamental building blocks for Compose UI including basic layout components,
                // gestures, scrolling, focus handling, and other foundational primitives that higher-level
                // components are built upon (e.g., `Box`, `Row`, `Column`, `LazyColumn`, `clickable`).
                implementation(libs.compose.foundation)

                // Compose Components Resources
                // Enables access to resources (strings, images, fonts, etc.) in a multiplatform way.
                // Provides APIs to load and use resources consistently across different platforms
                // in Compose Multiplatform applications.
                implementation(libs.compose.components.resources)

                // Material Design 3
                // It provides a collection of ready-to-use composable UI elements, such as buttons, text fields,
                // cards, layouts, and themes, all adhering to Material 3 guidelines.
                implementation(libs.material3)

                // Compose Material 3 Adaptive Navigation Suite
                // This library provides a comprehensive navigation suite that adapts to different screen sizes
                // and device configurations. It includes NavigationBar, NavigationRail, and NavigationDrawer
                // components that automatically adjust based on window size classes, providing an optimal
                // navigation experience across phones, tablets, and foldable devices.
                implementation(libs.material3.adaptive.navigation.suite)

                // Material Design Icons Extended
                // Provides a large collection of icons that you can directly use as composables in your UI.
                implementation(libs.material3.icons.extended)

                // Compose Material 3 Adaptive
                // This library helps you create adaptive UIs that automatically adjust to different window
                // configurations, such as varying window sizes and device postures (e.g., folded, unfolded).
                // It offers both pre-built scaffold implementations and fundamental composable components for
                // crafting custom adaptive experiences.
                implementation(libs.material3.adaptive)

                // Material 3 Window Size Class
                // It allows you to easily implement adaptive UI designs by providing the size class,
                // making it easy to have different layouts in different screen sizes.
                implementation(libs.material3.window.size)

                // Lifecycle Runtime for Compose
                // Lifecycle-aware bindings for Compose: adds helpers such as
                // `collectAsStateWithLifecycle`, `LocalLifecycleOwner`, and lifecycle-aware
                // effect utilities (`LifecycleStartEffect`, `LifecycleResumeEffect`).
                // Complements `compose.runtime` by bridging AndroidX Lifecycle (or its KMP port) with Compose.
                implementation(libs.lifecycle.runtime.compose)

                // Integration with ViewModels
                // This library provides the `viewModel()` function that is used to access a ViewModel instance
                // from your composables. This library manage the viewModel scope, and let you access it from compose.
                implementation(libs.lifecycle.viewmodel.compose)

                // Compose Navigation
                // This is the core library for implementing navigation within a Compose-based app.
                // It provides composable functions for defining your navigation graph, handling navigation
                // actions, and passing data between screens.
                implementation(libs.nav3)
                implementation(libs.nav3.lifecycle.viewmodel)

                // Compose Material 3 Adaptive Navigation
                // This library provides components to build adaptive navigation UIs.
                // It offers pre-built navigation components like NavigationRail, NavigationBar, and more
                // that adjust their behavior and appearance based on the window size and device posture.
                implementation(libs.nav3.material3.adaptive)

                implementation(libs.compose.animation)
            }

            // androidMain only exists when an Android target is registered.
            // Single-target KMP modules (wasmApp, jvmApp) skip this block.
            findByName("androidMain")?.dependencies {
                // Compose UI Tooling — @Preview rendering in Android Studio Narwhal+
                implementation(libs.compose.ui.tooling)
            }

            findByName("jvmTest")?.dependencies {
                // Skiko / AWT runtime required by `runComposeUiTest` on the JVM.
                implementation(compose.desktop.currentOs)
            }

            commonTest.dependencies {
                // Kotlin Test Framework
                // Provides common testing utilities and assertions for Kotlin multiplatform projects.
                // Includes basic test annotations (@Test), assertion functions (assertEquals, assertTrue, etc.),
                // and platform-agnostic testing capabilities that work across JVM, JS, and Native targets.
                implementation(libs.kotlin.test)

                // Compose UI Testing
                // Provides testing utilities specifically for Compose UI components including semantic matchers,
                // test rules, and assertion functions for verifying UI behavior and state in automated tests.
                // Essential for writing unit and integration tests for Compose-based user interfaces.
                implementation(libs.compose.ui.test)
            }
        }
    }
}

fun Project.configureComposeResources(packageResource: String) {
    val compose = extensions.getByType<ComposeExtension>()
    compose.extensions.configure<ResourcesExtension> {
        nameOfResClass = path.toResClassName()
        publicResClass = true
        packageOfResClass = packageResource
        generateResClass = auto
    }
}
