package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.runtime.staticCompositionLocalOf

enum class WindowType(val size: Int) {
    Compact(size = 600),
    Medium(size = 840),
    Expanded(size = 1200)
}

val LocalWindowType = staticCompositionLocalOf<WindowType> {
    error("No WindowType provided")
}
