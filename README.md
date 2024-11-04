# Spike Markdown

## Project Overview

This project is a simple Kotlin application designed to test and compare various Markdown converter libraries. The goal is to evaluate the capabilities and performance of different libraries in converting HTML to Markdown.

### Tests
The tests are designed to verify the correctness and performance of the Markdown conversion libraries. They include:  
- **CommonMarkTest**: Tests the conversion of HTML to Markdown using the CommonMark library.
- **FlexmarkTest**: Tests the conversion of HTML to Markdown using the Flexmark library.
- **JetBrainsMarkdownTest**: Tests the conversion of HTML to Markdown using the JetBrains Markdown library.

Each test case includes various HTML snippets and checks if the converted Markdown output matches the expected result.  
#### Purpose
The primary purpose of this project is to experiment with different Markdown converter libraries and understand their strengths and weaknesses. This can help in selecting the right library for future projects that require HTML to Markdown conversion.  

## Libraries Used

The project utilizes the following Markdown converter libraries:

- **CommonMark**: A highly compatible implementation of the Markdown specification.
- **Flexmark**: A comprehensive Markdown parser and renderer.
- **JetBrains Markdown**: A library developed by JetBrains for Markdown processing.

## Project Structure

The project is structured as follows:

- **src/main/kotlin**: Contains the main application code.
- **src/test/kotlin**: Contains the test cases for verifying the Markdown conversion.

## Dependencies

The project uses Gradle for dependency management. The key dependencies are:

- `org.commonmark:commonmark`
- `com.vladsch.flexmark:flexmark-all`
- `org.jetbrains:markdown`
- `org.junit.jupiter:junit-jupiter-api`

## Running Tests

To run the tests, use the following Gradle command:

```sh
./gradlew test