package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ScreenSizeParameterProvider : PreviewParameterProvider<ScreenSize> {
    override val values = sequenceOf(
        ScreenSize.Compact,
        ScreenSize.Medium,
        ScreenSize.Expanded
    )
}
