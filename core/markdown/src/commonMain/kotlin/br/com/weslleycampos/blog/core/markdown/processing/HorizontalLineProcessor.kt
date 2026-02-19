package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.koin.core.annotation.Single

@Single
class HorizontalLineProcessor : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? {
        return when (node.type) {
            MarkdownTokenTypes.HORIZONTAL_RULE -> MarkdownBlock.ThematicBreak
            else -> null
        }
    }
}
