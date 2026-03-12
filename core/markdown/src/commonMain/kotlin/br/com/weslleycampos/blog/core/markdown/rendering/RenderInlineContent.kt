package br.com.weslleycampos.blog.core.markdown.rendering

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import br.com.weslleycampos.blog.core.markdown.model.InlineMarkdown
import br.com.weslleycampos.blog.core.markdown.styling.LocalMarkdownStyling
import br.com.weslleycampos.blog.core.markdown.styling.MarkdownStyling

@Composable
fun List<InlineMarkdown>.toAnnotatedString(): AnnotatedString {
    val styling = LocalMarkdownStyling.current

    return buildAnnotatedString {
        this@toAnnotatedString.forEach { inline ->
            appendInline(inline, styling)
        }
    }
}

private fun AnnotatedString.Builder.appendInline(
    inline: InlineMarkdown,
    styling: MarkdownStyling
) {
    when (inline) {
        is InlineMarkdown.Text -> append(inline.content)
        is InlineMarkdown.Bold -> {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                inline.children.forEach { appendInline(it, styling) }
            }
        }
        is InlineMarkdown.Italic -> {
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                inline.children.forEach { appendInline(it, styling) }
            }
        }
        is InlineMarkdown.Strikethrough -> {
            withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                inline.children.forEach { appendInline(it, styling) }
            }
        }
        is InlineMarkdown.Code -> {
            withStyle(
                SpanStyle(
                    fontFamily = styling.codeFontFamily,
                    background = styling.codeBackgroundColor,
                    fontSize = styling.codeTextSize
                )
            ) {
                append(inline.content)
            }
        }
        is InlineMarkdown.Link -> {
            pushStringAnnotation(tag = "URL", annotation = inline.destination)
            withStyle(
                SpanStyle(
                    color = styling.linkColor,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                inline.children.forEach { appendInline(it, styling) }
            }
            pop()
        }
        is InlineMarkdown.LineBreak -> append("\n")
        is InlineMarkdown.SoftBreak -> append("")
        is InlineMarkdown.Image -> append("[${inline.alt}]")
    }
}
