package br.com.weslleycampos.blog.core.markdown.rendering.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import br.com.weslleycampos.blog.core.markdown.processing.CodeHighlightProcessor
import br.com.weslleycampos.blog.core.markdown.processing.CodeSnippetProcessor
import br.com.weslleycampos.blog.core.markdown.processing.HeadingProcessor
import br.com.weslleycampos.blog.core.markdown.processing.HorizontalLineProcessor
import br.com.weslleycampos.blog.core.markdown.processing.InlineExtractor
import br.com.weslleycampos.blog.core.markdown.processing.ListProcessor
import br.com.weslleycampos.blog.core.markdown.processing.MarkdownParser
import br.com.weslleycampos.blog.core.markdown.processing.ParagraphProcessor
import br.com.weslleycampos.blog.core.markdown.processing.QuoteProcessor
import br.com.weslleycampos.blog.core.markdown.processing.TableProcessor
import br.com.weslleycampos.blog.core.markdown.rendering.Markdown
import br.com.weslleycampos.blog.core.markdown.styling.LocalMarkdownStyling
import br.com.weslleycampos.blog.core.markdown.styling.MarkdownStyling
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

private val sampleMarkdown = """
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
    |## Code Examples
    |
    |```kotlin
    |val parser = MarkdownParser(
    |    processors = persistentListOf(
    |        HeadingProcessor(inlineExtractor),
    |    )
    |)
    |```
    |
    |## Block Quotes
    |
    |> The parser converts each markdown element into a sealed class.
    |> This makes it **type-safe** and easy to render.
    |
    |Nested quotes:
    |
    |> Outer quote
    |>> Inner nested quote
    |
    |## Table
    |
    || Processor | Block Type | Has Children |
    || :--- | :---: | ---: |
    || HeadingProcessor | Heading | No |
    || QuoteProcessor | Blockquote | Yes |
    |
    |## Lists
    |
    |- Type-safe sealed classes
    |- Kotlin Multiplatform support
    |- Extensible processor architecture
    |
    |1. Add the dependency
    |2. Create the processor list
    |3. Instantiate the parser
    |
    |## Task List
    |
    |- [x] Implement MarkdownParser
    |- [x] Add unit tests
    |- [ ] Implement rendering layer
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
    |This module provides a clean, extensible way to parse markdown. Check the [source code](https://github.com/example/blog).
""".trimMargin()

@Preview(showBackground = true)
@Composable
private fun MarkdownParserPreview() {
    val blocks = remember {
        val inlineExtractor = InlineExtractor()
        val parser = MarkdownParser(
            processors = listOf(
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
        parser.parse(sampleMarkdown)
    }

    BlogTheme(isDarkMode = false) {
        CompositionLocalProvider(LocalMarkdownStyling provides MarkdownStyling.fromTheme()) {
            Markdown(tree = blocks)
        }
    }
}
