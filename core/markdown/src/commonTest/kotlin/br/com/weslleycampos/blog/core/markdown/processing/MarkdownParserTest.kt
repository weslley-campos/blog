package br.com.weslleycampos.blog.core.markdown.processing

import br.com.weslleycampos.blog.core.markdown.model.InlineMarkdown
import br.com.weslleycampos.blog.core.markdown.model.MarkdownBlock
import kotlinx.collections.immutable.persistentListOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MarkdownParserTest {

    private val inlineExtractor = InlineExtractor()

    private val parser = MarkdownParser(
        processors = persistentListOf(
            HeadingProcessor(inlineExtractor),
            ParagraphProcessor(inlineExtractor),
            CodeSnippetProcessor(),
            CodeHighlightProcessor(),
            QuoteProcessor(),
            ListProcessor(),
            HorizontalLineProcessor(),
            TableProcessor(inlineExtractor),
        )
    )

    private val article = """
        |# Markdown Parser Module
        |
        |## Overview
        |
        |The **Markdown Parser** module is responsible for converting *raw markdown* into a structured `List<MarkdownBlock>` that can be rendered by Compose UI. It replaces the ~~old regex-based~~ approach.
        |
        |### How It Works
        |
        |The parser uses a **two-pass architecture** inspired by [Jewel Markdown](https://github.com/JetBrains/jewel):
        |
        |1. Parse raw markdown into an AST
        |2. Convert AST nodes into sealed `MarkdownBlock` types
        |3. Render blocks via Compose composables
        |
        |## Supported Block Types
        |
        |The following block types are supported:
        |
        |- Headings (H1 through H6)
        |- Paragraphs with **bold**, *italic*, and `inline code`
        |- Fenced code blocks with syntax highlighting
        |- Block quotes
        |- Ordered and unordered lists
        |    - Including nested sublists
        |    - With multiple levels
        |- Tables with alignment
        |- Horizontal rules
        |
        |## Code Examples
        |
        |Here is how you create the parser in Kotlin:
        |
        |```kotlin
        |val parser = MarkdownParser(
        |    processors = persistentListOf(
        |        HeadingProcessor(inlineExtractor),
        |        ParagraphProcessor(inlineExtractor),
        |    )
        |)
        |```
        |
        |And here is a Python example for comparison:
        |
        |```python
        |import markdown
        |
        |md = markdown.Markdown()
        |result = md.convert("# Hello")
        |```
        |
        |You can also use a block without specifying a language:
        |
        |```
        |just plain code
        |no language specified
        |```
        |
        |#### Deep Heading Example
        |
        |##### Even Deeper
        |
        |###### The Deepest Level
        |
        |## Block Quotes
        |
        |> The parser converts each markdown element into a sealed class.
        |> This makes it **type-safe** and easy to render.
        |
        |Nested quotes are also supported:
        |
        |> Outer quote
        |>> Inner nested quote
        |
        |## Processor Comparison
        |
        || Processor | Block Type | Has Children |
        || :--- | :---: | ---: |
        || HeadingProcessor | Heading | No |
        || ParagraphProcessor | Paragraph | No |
        || CodeSnippetProcessor | CodeBlock | No |
        || QuoteProcessor | Blockquote | Yes |
        || ListProcessor | ListBlock | Yes |
        |
        |## Lists
        |
        |Unordered features:
        |
        |- Type-safe sealed classes
        |- Kotlin Multiplatform support
        |- Extensible processor architecture
        |
        |Nested unordered list:
        |
        |- Block types
        |    - Heading
        |    - Paragraph
        |    - CodeBlock
        |- Inline types
        |    - Bold
        |    - Italic
        |    - Code
        |    - Link
        |
        |Steps to integrate:
        |
        |1. Add the dependency to your module
        |2. Create the processor list
        |    1. Choose which processors to include
        |    2. Order them by priority
        |3. Instantiate the parser
        |4. Call `parser.parse(markdown)`
        |
        |Custom start numbers work too:
        |
        |5. This starts at five
        |6. Then six
        |7. And seven
        |
        |## Task List
        |
        |Implementation progress:
        |
        |- [x] Implement MarkdownParser
        |- [x] Implement InlineExtractor
        |- [x] Add unit tests
        |- [ ] Implement rendering layer
        |- [ ] Add styling system
        |
        |## Images
        |
        |![Architecture diagram](https://example.com/architecture.png)
        |
        |## Alerts
        |
        |> [!NOTE]
        |> This is an informational note about the parser.
        |
        |> [!WARNING]
        |> Be careful with large markdown files.
        |
        |---
        |
        |## Conclusion
        |
        |This module provides a clean, extensible way to parse markdown into structured data. For more details, check the [source code](https://github.com/example/blog).
    """.trimMargin()

    @Test
    fun `parse full article structure`() {
        val result = parser.parse(article)

        assertTrue(result.isNotEmpty())

        val types = result.map { it::class.simpleName }
        assertTrue("Heading" in types)
        assertTrue("Paragraph" in types)
        assertTrue("CodeBlock" in types)
        assertTrue("Blockquote" in types)
        assertTrue("ListBlock" in types)
        assertTrue("ThematicBreak" in types)
        assertTrue("Table" in types)
        assertTrue("Alert" in types)
    }

    @Test
    fun `parse headings from article`() {
        val result = parser.parse(article)
        val headings = result.filterIsInstance<MarkdownBlock.Heading>()

        val levels = headings.map { it.level }
        assertTrue(1 in levels, "Should contain H1")
        assertTrue(2 in levels, "Should contain H2")
        assertTrue(3 in levels, "Should contain H3")
        assertTrue(4 in levels, "Should contain H4")
        assertTrue(5 in levels, "Should contain H5")
        assertTrue(6 in levels, "Should contain H6")
    }

    @Test
    fun `parse h1 with correct text`() {
        val result = parser.parse(article)
        val h1 = result.filterIsInstance<MarkdownBlock.Heading>().first { it.level == 1 }

        assertInlineText("Markdown Parser Module", h1.inlineContent)
    }

    @Test
    fun `parse h3 with correct text`() {
        val result = parser.parse(article)
        val h3 = result.filterIsInstance<MarkdownBlock.Heading>().first { it.level == 3 }

        assertInlineText("How It Works", h3.inlineContent)
    }

    @Test
    fun `parse paragraph with bold and italic`() {
        val result = parser.parse(article)
        val paragraphs = result.filterIsInstance<MarkdownBlock.Paragraph>()

        val overviewParagraph = paragraphs.first { paragraph ->
            paragraph.inlineContent.any { inline ->
                inline is InlineMarkdown.Bold &&
                    inline.children.any { it is InlineMarkdown.Text && it.content == "Markdown Parser" }
            }
        }

        val hasItalic = overviewParagraph.inlineContent.any { it is InlineMarkdown.Italic }
        assertTrue(hasItalic, "Overview paragraph should contain italic text")

        val hasInlineCode = overviewParagraph.inlineContent.any { it is InlineMarkdown.Code }
        assertTrue(hasInlineCode, "Overview paragraph should contain inline code")
    }

    @Test
    fun `parse paragraph with link`() {
        val result = parser.parse(article)
        val paragraphs = result.filterIsInstance<MarkdownBlock.Paragraph>()

        val linkParagraph = paragraphs.first { paragraph ->
            paragraph.inlineContent.any { it is InlineMarkdown.Link }
        }

        val link = linkParagraph.inlineContent.filterIsInstance<InlineMarkdown.Link>().first()
        assertEquals("https://github.com/JetBrains/jewel", link.destination)
    }

    @Test
    fun `parse multiple code blocks with different languages`() {
        val result = parser.parse(article)
        val codeBlocks = result.filterIsInstance<MarkdownBlock.CodeBlock>()

        assertTrue(codeBlocks.size >= 3, "Should have at least 3 code blocks")

        val kotlinBlock = codeBlocks.first { it.language == "kotlin" }
        assertTrue(kotlinBlock.content.contains("MarkdownParser"))

        val pythonBlock = codeBlocks.first { it.language == "python" }
        assertTrue(pythonBlock.content.contains("markdown"))

        val plainBlock = codeBlocks.first { it.language.isNullOrBlank() }
        assertTrue(plainBlock.content.contains("just plain code"))
    }

    @Test
    fun `parse code block preserves multiline content`() {
        val result = parser.parse(article)
        val kotlinBlock = result.filterIsInstance<MarkdownBlock.CodeBlock>()
            .first { it.language == "kotlin" }

        assertTrue(kotlinBlock.content.contains("val parser"))
        assertTrue(kotlinBlock.content.contains("HeadingProcessor"))
    }

    @Test
    fun `parse blockquote with paragraph children`() {
        val result = parser.parse(article)
        val blockquotes = result.filterIsInstance<MarkdownBlock.Blockquote>()

        assertTrue(blockquotes.isNotEmpty())

        val firstQuote = blockquotes.first()
        val paragraphs = firstQuote.children.filterIsInstance<MarkdownBlock.Paragraph>()
        assertTrue(paragraphs.isNotEmpty(), "Blockquote should contain paragraphs")
    }

    @Test
    fun `parse nested blockquote`() {
        val result = parser.parse(article)
        val blockquotes = result.filterIsInstance<MarkdownBlock.Blockquote>()

        val nested = blockquotes.any { outer ->
            outer.children.any { it is MarkdownBlock.Blockquote }
        }
        assertTrue(nested, "Should have a nested blockquote")
    }

    @Test
    fun `parse table with header and rows`() {
        val result = parser.parse(article)
        val table = result.filterIsInstance<MarkdownBlock.Table>().first()

        assertEquals(3, table.header.cells.size, "Table should have 3 columns")
        assertEquals(5, table.rows.size, "Table should have 5 data rows")
    }

    @Test
    fun `parse table alignments`() {
        val result = parser.parse(article)
        val table = result.filterIsInstance<MarkdownBlock.Table>().first()

        assertEquals(MarkdownBlock.Table.Alignment.LEFT, table.alignments[0])
        assertEquals(MarkdownBlock.Table.Alignment.CENTER, table.alignments[1])
        assertEquals(MarkdownBlock.Table.Alignment.RIGHT, table.alignments[2])
    }

    @Test
    fun `parse table header content`() {
        val result = parser.parse(article)
        val table = result.filterIsInstance<MarkdownBlock.Table>().first()

        val headerTexts = table.header.cells.map { cell ->
            cell.inlineContent.filterIsInstance<InlineMarkdown.Text>()
                .joinToString("") { it.content }.trim()
        }
        assertTrue("Processor" in headerTexts)
        assertTrue("Block Type" in headerTexts)
        assertTrue("Has Children" in headerTexts)
    }

    @Test
    fun `parse unordered list`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val unordered = lists.first { !it.ordered }
        assertTrue(unordered.items.size >= 3)
    }

    @Test
    fun `parse unordered list with nested sublist`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val hasNestedList = lists.filter { !it.ordered }.any { list ->
            list.items.any { item ->
                item.children.any { it is MarkdownBlock.ListBlock }
            }
        }
        assertTrue(hasNestedList, "Should have an unordered list with nested sublist")
    }

    @Test
    fun `parse ordered list`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val ordered = lists.first { it.ordered && it.startNumber == 1 }
        assertTrue(ordered.items.size >= 3)
    }

    @Test
    fun `parse ordered list with nested ordered sublist`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val hasNestedOrdered = lists.filter { it.ordered }.any { list ->
            list.items.any { item ->
                item.children.any { it is MarkdownBlock.ListBlock && it.ordered }
            }
        }
        assertTrue(hasNestedOrdered, "Should have an ordered list with nested ordered sublist")
    }

    @Test
    fun `parse ordered list with custom start number`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val customStart = lists.first { it.ordered && it.startNumber == 5 }
        assertEquals(3, customStart.items.size)
    }

    @Test
    fun `parse task list with checked and unchecked items`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val taskList = lists.first { list ->
            list.items.any { it.checked != null }
        }

        val checkedItems = taskList.items.filter { it.checked == true }
        val uncheckedItems = taskList.items.filter { it.checked == false }

        assertEquals(3, checkedItems.size, "Should have 3 checked items")
        assertEquals(2, uncheckedItems.size, "Should have 2 unchecked items")
    }

    @Test
    fun `parse task list items have correct checked state`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val taskList = lists.first { list ->
            list.items.any { it.checked != null }
        }

        assertEquals(true, taskList.items[0].checked)
        assertEquals(true, taskList.items[1].checked)
        assertEquals(true, taskList.items[2].checked)
        assertEquals(false, taskList.items[3].checked)
        assertEquals(false, taskList.items[4].checked)
    }

    @Test
    fun `parse regular list items have null checked state`() {
        val result = parser.parse(article)
        val lists = result.filterIsInstance<MarkdownBlock.ListBlock>()

        val regularList = lists.first { list ->
            !list.ordered && list.items.all { it.checked == null }
        }

        assertTrue(regularList.items.isNotEmpty())
        assertTrue(regularList.items.all { it.checked == null })
    }

    @Test
    fun `parse horizontal rule`() {
        val result = parser.parse(article)

        val hasThematicBreak = result.any { it is MarkdownBlock.ThematicBreak }
        assertTrue(hasThematicBreak, "Article should contain a thematic break")
    }

    @Test
    fun `parse image as inline content in paragraph`() {
        val result = parser.parse(article)
        val paragraphs = result.filterIsInstance<MarkdownBlock.Paragraph>()

        val imageParagraph = paragraphs.first { paragraph ->
            paragraph.inlineContent.any { it is InlineMarkdown.Image }
        }

        val image = imageParagraph.inlineContent
            .filterIsInstance<InlineMarkdown.Image>().first()
        assertEquals("https://example.com/architecture.png", image.source)
        assertEquals("Architecture diagram", image.alt)
    }

    @Test
    fun `parse alert note type`() {
        val result = parser.parse(article)
        val alerts = result.filterIsInstance<MarkdownBlock.Alert>()

        assertTrue(alerts.isNotEmpty(), "Should have at least one alert")

        val note = alerts.first { it.type == MarkdownBlock.Alert.AlertType.NOTE }
        assertTrue(note.children.isNotEmpty(), "Alert should have children")
    }

    @Test
    fun `parse alert warning type`() {
        val result = parser.parse(article)
        val alerts = result.filterIsInstance<MarkdownBlock.Alert>()

        val warning = alerts.first { it.type == MarkdownBlock.Alert.AlertType.WARNING }
        assertTrue(warning.children.isNotEmpty(), "Warning alert should have children")
    }

    @Test
    fun `parse multiple alert types`() {
        val result = parser.parse(article)
        val alerts = result.filterIsInstance<MarkdownBlock.Alert>()

        assertEquals(2, alerts.size, "Should have 2 alerts")

        val types = alerts.map { it.type }.toSet()
        assertTrue(MarkdownBlock.Alert.AlertType.NOTE in types)
        assertTrue(MarkdownBlock.Alert.AlertType.WARNING in types)
    }

    @Test
    fun `parse empty string returns empty list`() {
        val result = parser.parse("")

        assertTrue(result.isEmpty())
    }

    private fun assertInlineText(expected: String, inlineContent: List<InlineMarkdown>) {
        val text = inlineContent.filterIsInstance<InlineMarkdown.Text>()
            .joinToString("") { it.content }
        assertEquals(expected, text.trim())
    }
}
