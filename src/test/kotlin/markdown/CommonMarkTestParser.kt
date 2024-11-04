package markdown

import org.commonmark.parser.Parser
import org.commonmark.node.Node
import org.commonmark.renderer.text.TextContentRenderer

class CommonMarkTestParser : MarkdownTestParser {

    private val parser = Parser.builder().build()
    private val textRenderer = TextContentRenderer.builder().build()

    override fun parseHtmlToMarkdown(html: String): String {
        // Parse HTML input into a CommonMark document
        val document: Node = parser.parse(html)

        // Render the document to Markdown
        return textRenderer.render(document)
    }
}
