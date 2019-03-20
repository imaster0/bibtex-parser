package org.bibtex.parser.tests;

import org.bibtex.parser.Category;
import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.RecordFactory;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;
import org.bibtex.parser.records.Article;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    @Test
    void shouldThrowExceptionWhenMissingRequiredField() {
        Record article = new Article( new ArrayList<>());
        assertThrows(MissingFieldException.class, article::validate);
    }
}