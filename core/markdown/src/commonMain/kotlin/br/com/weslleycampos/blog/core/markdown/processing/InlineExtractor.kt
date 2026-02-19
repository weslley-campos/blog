package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.InlineMarkdown
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.gfm.GFMElementTypes
import org.koin.core.annotation.Single

@Single
class InlineExtractor {
    fun extract(node: ASTNode, source: String): ImmutableList<InlineMarkdown> {
        return node.children.mapNotNull { child ->
            when (child.type) {
                MarkdownTokenTypes.TEXT -> InlineMarkdown.Text(
                    child.getTextInNode(source).toString()
                )
                MarkdownElementTypes.STRONG -> InlineMarkdown.Bold(extract(child, source))
                MarkdownElementTypes.EMPH -> InlineMarkdown.Italic(extract(child, source))
                MarkdownElementTypes.CODE_SPAN -> InlineMarkdown.Code(extractCodeSpanContent(child, source))
                MarkdownElementTypes.INLINE_LINK -> createInlineLink(child, source)
                MarkdownElementTypes.IMAGE -> createInlineImage(child, source)
                GFMElementTypes.STRIKETHROUGH -> InlineMarkdown.Strikethrough(extract(child, source))
                MarkdownTokenTypes.EOL -> InlineMarkdown.SoftBreak
                MarkdownTokenTypes.HARD_LINE_BREAK -> InlineMarkdown.LineBreak
                else -> null
            }
        }.toPersistentList()
    }

    private fun extractCodeSpanContent(node: ASTNode, source: String): String {
        return node.children
            .filter { it.type == MarkdownTokenTypes.TEXT || it.type == MarkdownTokenTypes.CODE_FENCE_CONTENT }
            .joinToString("") { it.getTextInNode(source).toString() }
            .ifEmpty { node.getTextInNode(source).toString().removeSurrounding("`") }
    }

    private fun createInlineImage(node: ASTNode, source: String): InlineMarkdown.Image? {
        val linkNode = node.children.find { it.type == MarkdownElementTypes.INLINE_LINK }
            ?: return null

        val destNode = linkNode.children.find { it.type == MarkdownElementTypes.LINK_DESTINATION }
        val titleNode = linkNode.children.find { it.type == MarkdownElementTypes.LINK_TITLE }
        val textNode = linkNode.children.find { it.type == MarkdownElementTypes.LINK_TEXT }

        return InlineMarkdown.Image(
            source = destNode?.getTextInNode(source)?.toString() ?: "",
            alt = textNode?.getTextInNode(source)?.toString()?.removeSurrounding("[", "]") ?: "",
            title = titleNode?.getTextInNode(source)?.toString()
        )
    }

    private fun createInlineLink(node: ASTNode, source: String): InlineMarkdown.Link {
        val linkTextNode = node.children.find { it.type == MarkdownElementTypes.LINK_TEXT }
        val linkDestNode = node.children.find { it.type == MarkdownElementTypes.LINK_DESTINATION }
        val linkTitleNode = node.children.find { it.type == MarkdownElementTypes.LINK_TITLE }

        val children = linkTextNode?.let { extract(it, source) } ?: emptyList()
        val destination = linkDestNode?.getTextInNode(source)?.toString() ?: ""
        val title = linkTitleNode?.getTextInNode(source)?.toString()

        return InlineMarkdown.Link(destination, title, children)
    }
}
