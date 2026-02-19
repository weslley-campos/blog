package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.koin.core.annotation.Single

@Single
class QuoteProcessor : Processor {
    private val alertPattern = Regex("^\\[!(NOTE|TIP|IMPORTANT|WARNING|CAUTION)]")

    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? = node.takeIf { it.type == MarkdownElementTypes.BLOCK_QUOTE }?.let {
        getAlertType(node, source)?.let { type ->
            createAlert(node, source, type, convertNode)
        } ?: createQuote(node, source, convertNode)
    }

    private fun createQuote(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock {
        val children = node.children
            .filter { it.type != MarkdownTokenTypes.BLOCK_QUOTE }
            .mapNotNull { convertNode(it, source) }

        return MarkdownBlock.Blockquote(children)
    }

    private fun getAlertType(node: ASTNode, source: String): MarkdownBlock.Alert.AlertType? {
        val firstParagraph = node.children
            .firstOrNull { it.type == MarkdownElementTypes.PARAGRAPH }
            ?: return null

        val text = firstParagraph.getTextInNode(source).toString().trim()
        return alertPattern.find(text)?.let { match ->
            MarkdownBlock.Alert.AlertType.valueOf(match.groupValues[1])
        }
    }

    private fun createAlert(
        node: ASTNode,
        source: String,
        alertType: MarkdownBlock.Alert.AlertType,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock.Alert {
        val children = node.children
            .filter { it.type != MarkdownTokenTypes.BLOCK_QUOTE }
            .mapNotNull { convertNode(it, source) }

        return MarkdownBlock.Alert(alertType, children)
    }
}
