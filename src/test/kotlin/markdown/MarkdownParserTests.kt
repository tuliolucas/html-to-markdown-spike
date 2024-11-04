package markdown

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MarkdownParserTests {

    private lateinit var parser: MarkdownTestParser

    @BeforeEach
    fun setup() {
        // Replace `CommonMarkParser` with the actual parser implementation class for each library
        parser = FlexmarkTestParser()  // Example, replace with actual implementation as needed
    }

    @Test
    fun `test headings`() {
        val html = "<h1>Welcome to Our App</h1>"
        val expectedMarkdown = "# Welcome to Our App\n\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test basic formatting with bold and italic`() {
        val html = "<p>This is a sample paragraph with <strong>bold text</strong> and <em>italicized text</em>.</p>"
        val expectedMarkdown = "This is a sample paragraph with **bold text** and *italicized text*.\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test unordered list`() {
        val html = """
            <ul>
                <li>Easy to use</li>
                <li>Supports multiple platforms</li>
                <li>Lightweight and fast</li>
            </ul>
        """.trimIndent()
        val expectedMarkdown = "* Easy to use\n* Supports multiple platforms\n* Lightweight and fast\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test ordered list`() {
        val html = """
            <ol>
                <li>Step One: Install the app</li>
                <li>Step Two: Set up your account</li>
                <li>Step Three: Start using features</li>
            </ol>
        """.trimIndent()
        val expectedMarkdown = "1. Step One: Install the app\n2. Step Two: Set up your account\n3. Step Three: Start using features\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test nested list`() {
        val html = """
            <ul>
                <li>Get groceries
                    <ul>
                        <li>Fruits</li>
                        <li>Vegetables</li>
                    </ul>
                </li>
                <li>Prepare meals</li>
            </ul>
        """.trimIndent()
        val expectedMarkdown = "* Get groceries\n  * Fruits\n  * Vegetables\n* Prepare meals\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test image with alt text`() {
        val html = """<img src="https://via.placeholder.com/150" alt="Placeholder Image">"""
        val expectedMarkdown = "![Placeholder Image](https://via.placeholder.com/150)\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test link with inline formatting`() {
        val html = """<p>Visit our <a href="https://example.com">website</a> for more information.</p>"""
        val expectedMarkdown = "Visit our [website](https://example.com) for more information.\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test blockquote and code block`() {
        val html = """
            <blockquote><p>This is a quoted text.</p></blockquote>
            <pre><code>fun greet() {
                println("Hello, World!")
            }</code></pre>
        """.trimIndent()
        val expectedMarkdown = "> This is a quoted text.\n" +
                "\n" +
                "    fun greet() {\n" +
                "        println(\"Hello, World!\")\n" +
                "    }\n" +
                "\n"
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }

    @Test
    fun `test complex HTML with links and images`() {
        val html = """
            <h2>Documentation</h2>
            <p>For the full guide, visit <a href="https://docs.example.com">our documentation site</a>.</p>
            <ul>
                <li>Introduction</li>
                <li>Setup Instructions
                    <ul>
                        <li>Download the installer</li>
                        <li>Follow <a href="https://example.com/guide">this guide</a> to configure</li>
                    </ul>
                </li>
                <li>Features Overview
                    <ul>
                        <li>Responsive Design</li>
                        <li>Cross-Platform Support
                            <ul>
                                <li>Android</li>
                                <li>iOS</li>
                                <li>Web</li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
            <img src="https://via.placeholder.com/200" alt="Sample Documentation Image">
        """.trimIndent()
        val expectedMarkdown = """
            ## Documentation
            
            For the full guide, visit [our documentation site](https://docs.example.com).
            
            * Introduction
            * Setup Instructions
              * Download the installer
              * Follow [this guide](https://example.com/guide) to configure
            * Features Overview
              * Responsive Design
              * Cross-Platform Support
                * Android
                * iOS
                * Web
            
            ![Sample Documentation Image](https://via.placeholder.com/200)
            
        """.trimIndent()
        assertEquals(expectedMarkdown, parser.parseHtmlToMarkdown(html))
    }
}
