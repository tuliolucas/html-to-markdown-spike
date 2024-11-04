package markdown

interface MarkdownTestParser {
    fun parseHtmlToMarkdown(html: String): String
}