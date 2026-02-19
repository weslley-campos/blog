package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.koin.core.annotation.Single

@Single
class CodeSnippetProcessor : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? = node.takeIf { it.type == MarkdownElementTypes.CODE_FENCE }?.let {
        val languageNode = node.children.find { it.type == MarkdownTokenTypes.FENCE_LANG }
        val language = languageNode?.getTextInNode(source)?.toString()?.trim()

        val contentNodes = node.children.filter { it.type == MarkdownTokenTypes.CODE_FENCE_CONTENT }
        val content = contentNodes.joinToString("\n") { it.getTextInNode(source).toString() }

        MarkdownBlock.CodeBlock(content, language)
    }
}
