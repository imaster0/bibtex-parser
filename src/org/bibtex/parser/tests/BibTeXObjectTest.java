package org.bibtex.parser.tests;

import org.bibtex.parser.BibTeXObject;
import org.bibtex.parser.BibTeXParser;
import org.bibtex.parser.Category;
import org.bibtex.parser.UndefinedRecordException;
import org.bibtex.parser.models.Record;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BibTeXObjectTest {

    @Test
    void shouldSearchByCategory() {
        String test = "@ARTICLE{key,\n" +
                "title = \"Test test\",\n" +
                "author = \"Imie Nazwisko\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}\n" +
                "@BOOK{key2,\n" +
                "title = \"Test test\",\n" +
                "author = \"Nazwisko | Imie\",\n" +
                "publisher = \"abc\",\n" +
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
        BibTeXObject object;
        try {
            object = BibTeXParser.parse(test);
            List<Record> records = object.searchBy("category", Category.ARTICLE.toString());
            assertEquals(3, records.size());
            List<Record> records2 = object.searchBy("category", Category.BOOK.toString());
            assertEquals(1, records2.size());
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    @Test
    void shouldSearchByAuthorSurname() {
        String test = "@ARTICLE{key,\n" +
                "title = \"Test test\",\n" +
                "author = \"Imie Kowalski\",\n" +
                "journal = \"abc\",\n" +
                "year = 1234,\n" +
                "}\n" +
                "@BOOK{key2,\n" +
                "title = \"Test test\",\n" +
                "author = \"Kowalski | Jan\",\n" +
                "publisher = \"abc\",\n" +
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
        List<Record> records = object.searchBy("author", "Kowalski");
        assertEquals(2, records.size());
    }
}