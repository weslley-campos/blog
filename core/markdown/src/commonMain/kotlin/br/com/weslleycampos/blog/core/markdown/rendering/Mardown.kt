package br.com.weslleycampos.blog.core.markdown.rendering

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import kotlinx.collections.immutable.ImmutableList

@Composable
fun Markdown(
    tree: ImmutableList<MarkdownBlock>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = tree) { block ->
            Render(block)
        }
    }
}

@Composable
internal fun Render(block: MarkdownBlock) {
    when (block) {
        is MarkdownBlock.Heading -> Render(heading = block)
        is MarkdownBlock.Paragraph -> Render(paragraph = block)
        else -> Unit
    }
}
