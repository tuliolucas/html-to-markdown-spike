package markdown

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter
import com.vladsch.flexmark.util.data.DataHolder
import com.vladsch.flexmark.util.data.MutableDataSet

class FlexmarkTestParser : MarkdownTestParser {

    // Define options and configure once, outside of the method to avoid redundancy
    private val options: DataHolder = MutableDataSet().apply {
        // Disable Setext headers to use ATX headers consistently
        set(FlexmarkHtmlConverter.SETEXT_HEADINGS, false)
    }

    // Initialize the converter with the configured options
    private val htmlConverter = FlexmarkHtmlConverter.builder(options).build()

    override fun parseHtmlToMarkdown(html: String): String {
        // Use the pre-configured converter instance for conversion
        return htmlConverter.convert(html)
    }

}
