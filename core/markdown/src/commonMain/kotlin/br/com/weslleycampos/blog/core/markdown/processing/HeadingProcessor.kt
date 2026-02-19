package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import kotlinx.collections.immutable.persistentListOf
import org.intellij.markdown.IElementType
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.koin.core.annotation.Single

@Single
class HeadingProcessor(
    private val inlineExtractor: InlineExtractor,
) : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? = HEADINGS.indexOf(node.type).takeIf { it >= 0 }?.let { index ->
        val level = index + 1
        val contentNode = node.children.find { it.type == MarkdownTokenTypes.ATX_CONTENT }
        val inlineContent =
            contentNode?.let { inlineExtractor.extract(it, source) } ?: persistentListOf()

        MarkdownBlock.Heading(level, inlineContent)
    }

    companion object {
        private val HEADINGS: List<IElementType> = persistentListOf(
            MarkdownElementTypes.ATX_1,
            MarkdownElementTypes.ATX_2,
            MarkdownElementTypes.ATX_3,
            MarkdownElementTypes.ATX_4,
            MarkdownElementTypes.ATX_5,
            MarkdownElementTypes.ATX_6,
        )
    }
}
