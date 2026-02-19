package br.com.weslleycampos.blog.core.markdown.model

sealed interface MarkdownBlock {

    data class Heading(
        val level: Int, // 1-6
        val inlineContent: List<InlineMarkdown>
    ) : MarkdownBlock

    data class Paragraph(
        val inlineContent: List<InlineMarkdown>
    ) : MarkdownBlock

    data class CodeBlock(
        val content: String,
        val language: String? = null
    ) : MarkdownBlock

    data class Blockquote(
        val children: List<MarkdownBlock>
    ) : MarkdownBlock

    data class ListBlock(
        val items: List<ListItem>,
        val ordered: Boolean,
        val startNumber: Int = 1
    ) : MarkdownBlock

    data class ListItem(
        val children: List<MarkdownBlock>,
        val checked: Boolean? = null
    ) : MarkdownBlock

    data object ThematicBreak : MarkdownBlock

    data class Table(
        val header: TableRow,
        val rows: List<TableRow>,
        val alignments: List<Alignment>
    ) : MarkdownBlock {
        enum class Alignment { LEFT, CENTER, RIGHT }
    }

    data class TableRow(
        val cells: List<TableCell>
    )

    data class TableCell(
        val inlineContent: List<InlineMarkdown>,
    )

    data class Alert(
        val type: AlertType,
        val children: List<MarkdownBlock>
    ) : MarkdownBlock {
        enum class AlertType { NOTE, TIP, IMPORTANT, WARNING, CAUTION }
    }
}
