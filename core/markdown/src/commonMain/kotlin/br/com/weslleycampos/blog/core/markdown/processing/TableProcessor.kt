package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.gfm.GFMElementTypes
import org.intellij.markdown.flavours.gfm.GFMTokenTypes
import org.koin.core.annotation.Single

@Single
class TableProcessor(
    private val inlineExtractor: InlineExtractor,
) : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? = node.takeIf { it.type == GFMElementTypes.TABLE }?.let {

        val headerNode = node.children.find { it.type == GFMElementTypes.HEADER }
            ?: return null
        val header = createTableRow(headerNode, source)

        val rows = node.children
            .filter { it.type == GFMElementTypes.ROW }
            .map { createTableRow(it, source) }

        val alignments = node.children
            .find { it.type == GFMTokenTypes.TABLE_SEPARATOR }
            ?.let { parseAlignments(it, source) }
            ?: List(header.cells.size) { MarkdownBlock.Table.Alignment.LEFT }

        MarkdownBlock.Table(header, rows, alignments)
    }

    private fun createTableRow(node: ASTNode, source: String): MarkdownBlock.TableRow {
        val cells = node.children
            .filter { it.type == GFMTokenTypes.CELL }
            .map { MarkdownBlock.TableCell(inlineExtractor.extract(it, source)) }
        return MarkdownBlock.TableRow(cells)
    }

    private fun parseAlignments(
        node: ASTNode,
        source: String
    ): List<MarkdownBlock.Table.Alignment> {
        return node.getTextInNode(source).toString()
            .split("|")
            .filter { it.isNotBlank() }
            .map { cell ->
                val trimmed = cell.trim()
                when {
                    trimmed.startsWith(":") && trimmed.endsWith(":") -> MarkdownBlock.Table.Alignment.CENTER
                    trimmed.endsWith(":") -> MarkdownBlock.Table.Alignment.RIGHT
                    else -> MarkdownBlock.Table.Alignment.LEFT
                }
            }
    }
}
