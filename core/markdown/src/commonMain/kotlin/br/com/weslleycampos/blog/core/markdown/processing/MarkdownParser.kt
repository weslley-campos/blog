package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser
import org.koin.core.annotation.Single

@Single
class MarkdownParser(private val processors: List<Processor>) {
    private val flavour = GFMFlavourDescriptor()
    private val parser = MarkdownParser(flavour)

    fun parse(markdown: String): ImmutableList<MarkdownBlock> {
        val astTree = parser.buildMarkdownTreeFromString(markdown)

        return convertToMarkDownBlocks(astTree, markdown)
    }

    private fun convertToMarkDownBlocks(
        node: ASTNode,
        source: String
    ): ImmutableList<MarkdownBlock> {
        return node.children.filter { it.type != MarkdownTokenTypes.EOL }
            .mapNotNull { child -> convertNode(child, source) }
            .toPersistentList()
    }

    private fun convertNode(node: ASTNode, source: String): MarkdownBlock? {
       return processors.firstNotNullOfOrNull { it.process(node, source, ::convertNode) }
    }
}
