package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.ast.ASTNode
import org.koin.core.annotation.Single

@Single
class ParagraphProcessor(
    private val inlineExtractor: InlineExtractor,
) : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? = node.takeIf { it.type == MarkdownElementTypes.PARAGRAPH }?.let {
        val inlineContent = inlineExtractor.extract(node, source)
        MarkdownBlock.Paragraph(inlineContent)
    }
}
