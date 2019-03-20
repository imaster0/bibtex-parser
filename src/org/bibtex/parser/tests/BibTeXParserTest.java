package org.bibtex.parser.tests;

import org.bibtex.parser.*;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BibTeXParserTest {

    @Test
    void shouldCreateObject() {
        String test = "@ARTICLE{key,\n" +
                "title = \"Test test\",\n" +
                "author = \"Test test\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}";
        BibTeXObject object = null;
        try {
            object = BibTeXParser.parse(test);
        } catch (UndefinedRecordException e) {
            e.printStackTrace();
        }
        List<Record> records = object.getAll();
        Record article = records.get(0);
        assertEquals(Category.ARTICLE, article.getCategory(), "Expected article record");
        assertEquals("Test test", article.getField("title").getValue());
        assertEquals("abc", article.getField("journal").getValue());
        assertEquals("1234", article.getField("year").getValue());
    }

    @Test
    void shouldIgnoreUknownFields() {
        String test = "@ARTICLE{key,\n" +
                "title = \"Test test\",\n" +
                "author = \"Test test\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "school = \"Test\",\n" +
                "}";
        BibTeXObject object = null;
        try {
            object = BibTeXParser.parse(test);
        } catch (UndefinedRecordException e) {
            e.printStackTrace();
        }
        List<Record> records = object.getAll();
        Record article = records.get(0);
        assertFalse(article.hasField("school"));
    }

    @Test
    void shouldHaveOptionalFields() {
        String test = "@ARTICLE{key,\n" +
                "title = \"Test test\",\n" +
                "author = \"Test test\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "pages = 13,\n" +
                "}";
        BibTeXObject object = null;
        try {
            object = BibTeXParser.parse(test);
        } catch (UndefinedRecordException e) {
            e.printStackTrace();
        }
        List<Record> records = object.getAll();
        Record article = records.get(0);
        assertTrue(article.hasField("pages"));
    }

    @Test
    void shouldParseAuthor() {
        String test = "@ARTICLE{key,\n" +
                "title = \"Test test\",\n" +
                "author = \"Imie Nazwisko\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}\n" +
                "@ARTICLE{key2,\n" +
                "title = \"Test test\",\n" +
                "author = \"Nazwisko | Imie\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}\n" +
                "@ARTICLE{key3,\n" +
                "title = \"Test test\",\n" +
                "author = \"Nazwisko | Title | Imie\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}\n" +
                "@ARTICLE{key4,\n" +
                "title = \"Test test\",\n" +
                "author = \"Nazwisko | Imie and Nazwisko2 | Title2 | Imie2 and Imie3 Nazwisko3\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}";

        BibTeXObject object = null;
        try {
            object = BibTeXParser.parse(test);
        } catch (UndefinedRecordException e) {
            e.printStackTrace();
        }
        List<Record> records = object.getAll();
        Record article = records.get(0);
        Record article2 = records.get(1);
        Record article3 = records.get(2);
        Record article4 = records.get(3);
        assertEquals("Imie Nazwisko", article.getField("author").getValue().trim());
        assertEquals("Imie Nazwisko", article2.getField("author").getValue().trim());
        assertEquals("Title Imie Nazwisko", article3.getField("author").getValue().trim());
        assertEquals("Imie Nazwisko\n\tTitle2 Imie2 Nazwisko2\n\tImie3 Nazwisko3",
                article4.getField("author").getValue().trim());
    }
}