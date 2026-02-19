package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.ast.ASTNode

interface Processor {
    fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock? = { _, _ -> null }
    ): MarkdownBlock?
}
