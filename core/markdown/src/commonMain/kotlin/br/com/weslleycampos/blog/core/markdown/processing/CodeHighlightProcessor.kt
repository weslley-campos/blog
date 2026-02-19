package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.koin.core.annotation.Single

@Single
class CodeHighlightProcessor : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? = node.takeIf { it.type == MarkdownElementTypes.CODE_BLOCK }?.let {
        val content = node.getTextInNode(source).toString()
            .lines()
            .joinToString("\n") { it.removePrefix("    ") }

        MarkdownBlock.CodeBlock(content)
    }
}
