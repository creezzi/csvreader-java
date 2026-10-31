package io.github.creezzi.csvreader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class CsvReaderTest {
    @Test
    void parsesSimpleRows() {
        assertEquals(List.of(List.of("a", "b"), List.of("1", "2")), CsvReader.parse("a,b\n1,2\n"));
    }

    @Test
    void handlesQuotesCommasAndEscapedQuotes() {
        assertEquals(List.of(List.of("x", "has, comma", "say \"hi\"")),
                CsvReader.parse("x,\"has, comma\",\"say \"\"hi\"\"\""));
    }

    @Test
    void handlesEmbeddedNewlinesAndCrlf() {
        assertEquals(List.of(List.of("a", "line1\nline2"), List.of("b", "c")),
                CsvReader.parse("a,\"line1\nline2\"\r\nb,c\r\n"));
    }

    @Test
    void keepsEmptyFields() {
        assertEquals(List.of(List.of("", "", ""), List.of("a", "", "")), CsvReader.parse(",,\na,,"));
    }

    @Test
    void noTrailingNewlineStillYieldsLastRow() {
        assertEquals(2, CsvReader.parse("a\nb").size());
        assertEquals(List.of(), CsvReader.parse(""));
    }

    @Test
    void customDelimiter() {
        assertEquals(List.of(List.of("a", "b;c")), CsvReader.parse("a\t\"b;c\"", '\t'));
    }

    @Test
    void unterminatedQuoteIsAnError() {
        assertThrows(IllegalArgumentException.class, () -> CsvReader.parse("\"abc"));
    }
}
