package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.runtime.staticCompositionLocalOf

enum class ScreenSize(val size: Int) {
    Compact(size = 600),
    Medium(size = 840),
    Expanded(size = 1200)
}

val LocalScreenSize = staticCompositionLocalOf<ScreenSize> {
    error("No ScreenSize provided")
}
