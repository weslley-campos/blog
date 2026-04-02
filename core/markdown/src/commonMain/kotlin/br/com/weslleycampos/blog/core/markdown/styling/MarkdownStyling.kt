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
import br.com.weslleycampos.blog.core.ui.theme.BlockquoteTextStyle
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.CodeTextStyle
import br.com.weslleycampos.blog.core.ui.theme.JetBrainsMonoFontFamily
import br.com.weslleycampos.blog.core.ui.theme.SyntaxDefault

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

            val spacing = BlogTheme.spacing
            val divider = colors.dividerGradientColor.copy(alpha = colors.dividerGradientOpacity)
            val codeStyle = CodeTextStyle

            return MarkdownStyling(
                // Heading
                h1Style = typography.displayLarge.copy(color = colors.textPrimary),
                h2Style = typography.headlineLarge.copy(color = colors.textPrimary),
                h3Style = typography.headlineSmall.copy(color = colors.textPrimary),
                h4Style = typography.titleLarge.copy(color = colors.textPrimary),
                h5Style = typography.titleMedium.copy(color = colors.textPrimary),
                h6Style = typography.titleSmall.copy(color = colors.textPrimary),
                // Paragraph
                paragraphStyle = typography.bodyLarge.copy(color = colors.textSecondary),
                // Inline code — JetBrains Mono with subtle background
                codeFontFamily = JetBrainsMonoFontFamily,
                codeBackgroundColor = colors.interactiveSecondaryFill,
                codeTextSize = codeStyle.fontSize,
                // Code block — always dark, monospace
                codeBlockStyle = codeStyle.copy(color = SyntaxDefault),
                codeBlockBackgroundColor = colors.codeBackground,
                codeBlockBorderColor = Color.Transparent,
                codeBlockPadding = spacing.lg,
                // Links
                linkColor = colors.textLink,
                // Blockquote — editorial Merriweather italic
                blockquoteStyle = BlockquoteTextStyle.copy(color = colors.textMuted),
                blockquoteBorderColor = colors.brand,
                blockquoteBorderWidth = 4.dp,
                blockquoteBackgroundColor = Color.Transparent,
                blockquotePadding = spacing.lg,
                // List
                listItemStyle = typography.bodyLarge.copy(color = colors.textSecondary),
                listBulletColor = colors.brand,
                listItemSpacing = spacing.xs,
                // Table
                tableHeaderStyle = typography.titleSmall.copy(color = colors.textPrimary),
                tableCellStyle = typography.bodyMedium.copy(color = colors.textSecondary),
                tableBorderColor = divider,
                tableHeaderBackgroundColor = colors.surfaceElevated,
                tableCellPadding = spacing.sm,
                // Thematic break
                thematicBreakColor = divider,
                thematicBreakThickness = 1.dp,
                // Alert
                alertNoteColor = colors.statusInfoAccent,
                alertTipColor = colors.statusSuccessAccent,
                alertImportantColor = colors.brand,
                alertWarningColor = colors.statusWarningAccent,
                alertCautionColor = colors.statusDangerAccent,
                alertBackgroundAlpha = 0.08f,
                alertBorderWidth = 4.dp,
                alertPadding = spacing.md,
            )
        }
    }
}

val LocalMarkdownStyling = staticCompositionLocalOf<MarkdownStyling> {
    error("No MarkdownStyling provided")
}
