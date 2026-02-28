package br.com.weslleycampos.blog.core.markdown.styling

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Immutable
data class MarkdownStyling(
    // Heading
    val h1Style: TextStyle,
    val h2Style: TextStyle,
    val h3Style: TextStyle,
    val h4Style: TextStyle,
    val h5Style: TextStyle,
    val h6Style: TextStyle,

    // Paragraph
    val paragraphStyle: TextStyle,

    // Inline code (inside text)
    val codeFontFamily: FontFamily,
    val codeBackgroundColor: Color,
    val codeTextSize: TextUnit,

    // Fenced / indented code block
    val codeBlockStyle: TextStyle,
    val codeBlockBackgroundColor: Color,
    val codeBlockBorderColor: Color,
    val codeBlockPadding: Dp,

    // Links
    val linkColor: Color,

    // Blockquote
    val blockquoteStyle: TextStyle,
    val blockquoteBorderColor: Color,
    val blockquoteBorderWidth: Dp,
    val blockquoteBackgroundColor: Color,
    val blockquotePadding: Dp,

    // List
    val listItemStyle: TextStyle,
    val listBulletColor: Color,
    val listItemSpacing: Dp,

    // Table
    val tableHeaderStyle: TextStyle,
    val tableCellStyle: TextStyle,
    val tableBorderColor: Color,
    val tableHeaderBackgroundColor: Color,
    val tableCellPadding: Dp,

    // Thematic break
    val thematicBreakColor: Color,
    val thematicBreakThickness: Dp,

    // Alert
    val alertNoteColor: Color,
    val alertTipColor: Color,
    val alertImportantColor: Color,
    val alertWarningColor: Color,
    val alertCautionColor: Color,
    val alertBackgroundAlpha: Float,
    val alertBorderWidth: Dp,
    val alertPadding: Dp,
) {
    companion object {
        @Composable
        fun fromTheme(): MarkdownStyling {
            val typography = BlogTheme.typography
            val colors = BlogTheme.colors

            val content = colors.content

            return MarkdownStyling(
                // Heading
                h1Style = typography.displayLarge.copy(color = content.heading),
                h2Style = typography.headlineLarge.copy(color = content.heading),
                h3Style = typography.headlineSmall.copy(color = content.heading),
                h4Style = typography.titleLarge.copy(color = content.heading),
                h5Style = typography.titleMedium.copy(color = content.heading),
                h6Style = typography.titleSmall.copy(color = content.heading),

                // Paragraph
                paragraphStyle = typography.bodyLarge.copy(color = content.body),

                // Inline code
                codeFontFamily = typography.labelLarge.fontFamily ?: FontFamily.Monospace,
                codeBackgroundColor = content.codeSurface,
                codeTextSize = typography.labelLarge.fontSize,

                // Code block
                codeBlockStyle = typography.labelLarge.copy(color = content.code),
                codeBlockBackgroundColor = content.codeSurface,
                codeBlockBorderColor = colors.border.divider,
                codeBlockPadding = 12.dp,

                // Links
                linkColor = content.link,

                // Blockquote
                blockquoteStyle = typography.displayMedium.copy(color = content.muted),
                blockquoteBorderColor = colors.border.divider,
                blockquoteBorderWidth = 4.dp,
                blockquoteBackgroundColor = Color.Transparent,
                blockquotePadding = 12.dp,

                // List
                listItemStyle = typography.bodyLarge.copy(color = content.body),
                listBulletColor = content.muted,
                listItemSpacing = 4.dp,

                // Table
                tableHeaderStyle = typography.bodyLarge.copy(
                    color = content.heading,
                    fontFamily = typography.titleSmall.fontFamily,
                ),
                tableCellStyle = typography.bodyLarge.copy(color = content.body),
                tableBorderColor = colors.border.divider,
                tableHeaderBackgroundColor = content.codeSurface,
                tableCellPadding = 8.dp,

                // Thematic break
                thematicBreakColor = colors.border.divider,
                thematicBreakThickness = 1.dp,

                // Alert
                alertNoteColor = colors.badge.info,
                alertTipColor = colors.badge.success,
                alertImportantColor = colors.app.primary,
                alertWarningColor = colors.badge.warning,
                alertCautionColor = colors.badge.error,
                alertBackgroundAlpha = 0.08f,
                alertBorderWidth = 4.dp,
                alertPadding = 12.dp,
            )
        }
    }
}

val LocalMarkdownStyling = staticCompositionLocalOf<MarkdownStyling> {
    error("No MarkdownStyling provided")
}
