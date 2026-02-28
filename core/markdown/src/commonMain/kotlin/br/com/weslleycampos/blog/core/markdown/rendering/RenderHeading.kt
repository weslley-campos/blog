package br.com.weslleycampos.blog.core.markdown.rendering

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import br.com.weslleycampos.blog.core.markdown.styling.LocalMarkdownStyling

@Composable
internal fun Render(
    heading: MarkdownBlock.Heading,
    modifier: Modifier = Modifier
) {
    val text = heading.inlineContent.toAnnotatedString()

    val styling = LocalMarkdownStyling.current
    val style = when (heading.level) {
        1 -> styling.h1Style
        2 -> styling.h2Style
        3 -> styling.h3Style
        4 -> styling.h4Style
        5 -> styling.h5Style
        else -> styling.h6Style
    }

    Text(
        text = text,
        style = style,
        modifier = modifier
    )
}
