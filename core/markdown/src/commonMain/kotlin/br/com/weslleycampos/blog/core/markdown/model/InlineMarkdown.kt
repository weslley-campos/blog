package br.com.weslleycampos.blog.core.markdown.model

sealed interface InlineMarkdown {

    data class Text(val content: String) : InlineMarkdown

    data class Bold(val children: List<InlineMarkdown>) : InlineMarkdown

    data class Italic(val children: List<InlineMarkdown>) : InlineMarkdown

    data class Strikethrough(val children: List<InlineMarkdown>) : InlineMarkdown

    data class Code(val content: String) : InlineMarkdown

    data class Link(
        val destination: String,
        val title: String?,
        val children: List<InlineMarkdown>
    ) : InlineMarkdown

    data class Image(
        val source: String,
        val alt: String,
        val title: String?
    ) : InlineMarkdown

    data object LineBreak : InlineMarkdown

    data object SoftBreak : InlineMarkdown
}
