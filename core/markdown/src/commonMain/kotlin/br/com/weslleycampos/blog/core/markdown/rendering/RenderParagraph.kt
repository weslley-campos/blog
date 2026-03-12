package br.com.weslleycampos.blog.core.markdown.rendering

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import br.com.weslleycampos.blog.core.markdown.styling.LocalMarkdownStyling

@Composable
internal fun Render(
    paragraph: MarkdownBlock.Paragraph,
    modifier: Modifier = Modifier
) {
    val text = paragraph.inlineContent.toAnnotatedString()
    val style = LocalMarkdownStyling.current.paragraphStyle

    Text(
        text = text,
        style = style,
        modifier = modifier
    )
}
