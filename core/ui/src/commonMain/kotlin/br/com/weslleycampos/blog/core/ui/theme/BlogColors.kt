package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

data object BlogColors

val LocalBlogColors = staticCompositionLocalOf<BlogColors> {
    error("No BlogColors provided")
}
