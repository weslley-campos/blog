# kotlin-markdown-parser

A Kotlin Multiplatform library that parses Markdown (CommonMark + GFM) into a type-safe AST. Built on top of [intellij-markdown](https://github.com/JetBrains/markdown), it converts raw AST nodes into sealed classes ready for rendering with Compose, Views, HTML, or any other UI framework.

## Targets

- JVM
- WASM/JS
- Android

## Features

### Block Elements

| Element | Markdown Syntax | Model |
|---|---|---|
| Headings | `# H1` through `###### H6` | `MarkdownBlock.Heading` |
| Paragraphs | Plain text | `MarkdownBlock.Paragraph` |
| Fenced code blocks | ` ```kotlin ` | `MarkdownBlock.CodeBlock` |
| Indented code blocks | 4-space indent | `MarkdownBlock.CodeBlock` |
| Block quotes | `> text` | `MarkdownBlock.Blockquote` |
| Ordered lists | `1. item` | `MarkdownBlock.ListBlock` |
| Unordered lists | `- item` | `MarkdownBlock.ListBlock` |
| Task lists (GFM) | `- [x] done` | `MarkdownBlock.ListItem` with `checked` |
| Tables (GFM) | `\| col \| col \|` | `MarkdownBlock.Table` |
| Alerts (GFM) | `> [!NOTE]` | `MarkdownBlock.Alert` |
| Horizontal rules | `---` | `MarkdownBlock.ThematicBreak` |

### Inline Elements

| Element | Markdown Syntax | Model |
|---|---|---|
| Bold | `**text**` | `InlineMarkdown.Bold` |
| Italic | `*text*` | `InlineMarkdown.Italic` |
| Strikethrough (GFM) | `~~text~~` | `InlineMarkdown.Strikethrough` |
| Inline code | `` `code` `` | `InlineMarkdown.Code` |
| Links | `[text](url)` | `InlineMarkdown.Link` |
| Images | `![alt](url)` | `InlineMarkdown.Image` |
| Line breaks | `\` or two spaces | `InlineMarkdown.LineBreak` |

## Installation

```kotlin
// build.gradle.kts
dependencies {
    implementation("br.com.weslleycampos:kotlin-markdown-parser:<version>")
}
```

## Usage

### Basic parsing

```kotlin
val parser = MarkdownParser(
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

val blocks: List<MarkdownBlock> = parser.parse("# Hello **World**")
// [Heading(level=1, inlineContent=[Text("Hello "), Bold([Text("World")])])]
```

### With Koin

```kotlin
@Module
@ComponentScan
class MarkdownModule
```

All processors and the parser are annotated with `@Single` and discovered automatically via `@ComponentScan`. Just include `MarkdownModule` in your Koin configuration.

### Iterating the result

```kotlin
val blocks = parser.parse(markdown)

blocks.forEach { block ->
    when (block) {
        is MarkdownBlock.Heading -> renderHeading(block.level, block.inlineContent)
        is MarkdownBlock.Paragraph -> renderParagraph(block.inlineContent)
        is MarkdownBlock.CodeBlock -> renderCode(block.content, block.language)
        is MarkdownBlock.Blockquote -> renderQuote(block.children)
        is MarkdownBlock.ListBlock -> renderList(block.items, block.ordered)
        is MarkdownBlock.Table -> renderTable(block.header, block.rows, block.alignments)
        is MarkdownBlock.Alert -> renderAlert(block.type, block.children)
        is MarkdownBlock.ThematicBreak -> renderDivider()
        is MarkdownBlock.ListItem -> { /* handled inside ListBlock */ }
    }
}
```

### Rendering inline content

```kotlin
fun renderInline(content: List<InlineMarkdown>) {
    content.forEach { inline ->
        when (inline) {
            is InlineMarkdown.Text -> append(inline.content)
            is InlineMarkdown.Bold -> bold { renderInline(inline.children) }
            is InlineMarkdown.Italic -> italic { renderInline(inline.children) }
            is InlineMarkdown.Strikethrough -> strikethrough { renderInline(inline.children) }
            is InlineMarkdown.Code -> code(inline.content)
            is InlineMarkdown.Link -> link(inline.destination) { renderInline(inline.children) }
            is InlineMarkdown.Image -> image(inline.source, inline.alt)
            is InlineMarkdown.LineBreak -> newline()
            is InlineMarkdown.SoftBreak -> space()
        }
    }
}
```

## Architecture

```
Raw Markdown ──> MarkdownParser ──> List<MarkdownBlock>
   (String)       + Processors       (Sealed Classes)
```

The parser uses a **processor chain** pattern. Each `Processor` handles one AST node type and returns `null` for everything else. The parser tries each processor in sequence and uses the first non-null result.

```kotlin
interface Processor {
    fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock? = { _, _ -> null }
    ): MarkdownBlock?
}
```

The `convertNode` callback enables recursive processing for nested structures (block quotes, lists) without circular dependencies.

### Writing a custom processor

```kotlin
@Single
class MathBlockProcessor : Processor {
    override fun process(
        node: ASTNode,
        source: String,
        convertNode: (ASTNode, String) -> MarkdownBlock?
    ): MarkdownBlock? {
        if (node.type != GFMElementTypes.BLOCK_MATH) return null
        val content = node.getTextInNode(source).toString()
            .removeSurrounding("$$").trim()
        return MyCustomBlock.Math(content)
    }
}
```

Add `@Single` and it will be discovered by Koin's `@ComponentScan` automatically.

## Design Decisions

**Processor chain over monolithic `when`**: Each processor is a single class with one responsibility. Adding a new block type means adding a new file, not modifying existing code.

**Merged detection over ordering**: Features that share the same AST node type (alerts/blockquotes, task lists/lists, images/paragraphs) are detected internally by the parent processor, avoiding dependency on registration order.

**Sealed classes over visitor pattern**: `MarkdownBlock` and `InlineMarkdown` are sealed interfaces with data classes. Kotlin's `when` exhaustiveness checking guarantees you handle every type at compile time.

**Immutable collections**: The parser returns `ImmutableList<MarkdownBlock>`, safe for concurrent access and Compose state.

## License

```
Apache License 2.0
```
