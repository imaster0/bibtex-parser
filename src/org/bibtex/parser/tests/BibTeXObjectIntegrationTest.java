package org.bibtex.parser.tests;

import org.bibtex.parser.BibTeXFileReader;
import org.bibtex.parser.BibTeXObject;
import org.bibtex.parser.BibTeXParser;
import org.bibtex.parser.UndefinedRecordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibTeXObjectIntegrationTest {
    @Test
    void shouldThrowExceptionWhenUndefinedRecord() {
        BibTeXFileReader fileReader = new BibTeXFileReader();
        String test;
        try {
            test = fileReader.readFile("C:/Users/Jakub/Desktop/studia/projekty/po/src/org/bibtex/parser/files/tests/unknownRecord.bib");
            assertThrows(UndefinedRecordException.class, () -> {
                BibTeXParser.parse(test);
            });
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}