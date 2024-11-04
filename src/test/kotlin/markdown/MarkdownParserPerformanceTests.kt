package markdown

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertTrue

class MarkdownParserPerformanceTests {

    private lateinit var parser: MarkdownTestParser

    @BeforeEach
    fun setup() {
        parser = FlexmarkTestParser()
    }

    @Test
    fun `performance test with large HTML snippet`() {
        val largeHtml = """
            <h1>Title</h1>
            <p>This is a large HTML snippet designed to test the performance of the Markdown library.</p>
            <ul>
                <li>Item 1</li>
                <li>Item 2
                    <ul>
                        <li>Sub-item 1</li>
                        <li>Sub-item 2</li>
                    </ul>
                </li>
                <li>Item 3</li>
            </ul>
            <p>End of large HTML snippet.</p>
        """.trimIndent().repeat(100)  // Repeat to simulate large input

        val startTime = System.nanoTime()
        parser.parseHtmlToMarkdown(largeHtml)
        val endTime = System.nanoTime()

        val durationMs = (endTime - startTime) / 1_000_000
        println("Execution time for large HTML snippet: $durationMs ms")

        // Check if processing time is reasonable, e.g., less than 500 ms (adjust as needed)
        assertTrue(durationMs < 500, "Markdown conversion took too long for large HTML input")
    }

    @Test
    fun `memory usage test with complex HTML snippet`() {
        val complexHtml = """
            <h2>Complex HTML Test</h2>
            <p>Testing with multiple nested elements.</p>
            <blockquote>
                <p>Quote with <strong>bold</strong> and <em>italic</em> text.</p>
                <ul>
                    <li>Nested list item 1
                        <ul>
                            <li>Deep nested item</li>
                        </ul>
                    </li>
                </ul>
            </blockquote>
            <img src="https://example.com/image.jpg" alt="Example Image">
        """.trimIndent()

        // Measure memory usage before and after parsing
        val runtime = Runtime.getRuntime()
        System.gc()  // Suggest garbage collection to clean up before measuring memory
        val memoryBefore = runtime.totalMemory() - runtime.freeMemory()

        parser.parseHtmlToMarkdown(complexHtml)

        System.gc()
        val memoryAfter = runtime.totalMemory() - runtime.freeMemory()
        val memoryUsedKb = (memoryAfter - memoryBefore) / 1024

        println("Memory usage for complex HTML snippet: $memoryUsedKb KB")

        // Check if memory usage is reasonable, e.g., less than 10 MB (adjust as needed)
        assertTrue(memoryUsedKb < 10240, "Markdown conversion used too much memory for complex HTML input")
    }

    @Test
    fun `high-frequency wparsing performance test`() {
        val simpleHtml = "<p>Quick conversion test.</p>"

        val startTime = System.nanoTime()
        repeat(1000) {
            parser.parseHtmlToMarkdown(simpleHtml)
        }
        val endTime = System.nanoTime()

        val durationMs = (endTime - startTime) / 1_000_000
        println("Execution time for 1000 simple HTML conversions: $durationMs ms")

        // Ensure the batch conversion is efficient, e.g., less than 200 ms (adjust as needed)
        assertTrue(durationMs < 500, "Markdown conversion took too long for high-frequency parsing: " + durationMs + "ms")
    }
    
    @Test
    fun `optimized high-frequency parsing performance test`() {
        val simpleHtml = "<p>Quick conversion test.</p>"

        // Track timing per call for better analysis
        val times = mutableListOf<Long>()
        repeat(100) {  // Reduced iterations for realistic performance measure
            val startTime = System.nanoTime()
            parser.parseHtmlToMarkdown(simpleHtml)
            val endTime = System.nanoTime()
            times.add((endTime - startTime) / 1_000_000) // Time per call in ms
        }

        // Compute average time and print results
        val averageTime = times.average()
        println("Average execution time for each HTML conversion: $averageTime ms")

        // Check that the average time per call is reasonable (e.g., < 2 ms per call)
        assertTrue(averageTime < 2, "Markdown conversion took too long for high-frequency parsing")
    }
}
