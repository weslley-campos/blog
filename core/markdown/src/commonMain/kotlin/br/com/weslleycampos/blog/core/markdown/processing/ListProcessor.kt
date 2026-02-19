package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.gfm.GFMTokenTypes
import org.koin.core.annotation.Single

@Single
class ListProcessor : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? {
        return when (node.type) {
            MarkdownElementTypes.ORDERED_LIST -> createOrderedList(node, source, convertNode)
            MarkdownElementTypes.UNORDERED_LIST -> createUnorderedList(node, source, convertNode)
            else -> null
        }
    }

    private fun createOrderedList(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock {
        val items = node.children
            .filter { it.type == MarkdownElementTypes.LIST_ITEM }
            .map { createListItem(it, source, convertNode) }

        val startNumber = node.children.firstOrNull { it.type == MarkdownElementTypes.LIST_ITEM }
            ?.children?.firstOrNull { it.type == MarkdownTokenTypes.LIST_NUMBER }
            ?.getTextInNode(source)
            ?.toString()
            ?.trim()
            ?.trimEnd('.')
            ?.toIntOrNull() ?: 1

        return MarkdownBlock.ListBlock(items, ordered = true, startNumber = startNumber)
    }

    private fun createUnorderedList(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock {
        val items = node.children
            .filter { it.type == MarkdownElementTypes.LIST_ITEM }
            .map { createListItem(it, source, convertNode) }
        return MarkdownBlock.ListBlock(items, ordered = false)
    }

    private fun createListItem(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock.ListItem {
        val checkBox = node.children.firstOrNull { it.type == GFMTokenTypes.CHECK_BOX }
        val checked = checkBox?.getTextInNode(source)
            ?.toString()?.contains("x", ignoreCase = true)

        val children = node.children.mapNotNull { child ->
            when (child.type) {
                MarkdownElementTypes.PARAGRAPH,
                MarkdownElementTypes.ORDERED_LIST,
                MarkdownElementTypes.UNORDERED_LIST,
                MarkdownElementTypes.CODE_FENCE -> convertNode(child, source)
                else -> null
            }
        }
        return MarkdownBlock.ListItem(children, checked)
    }
}
