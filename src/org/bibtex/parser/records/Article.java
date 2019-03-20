package org.bibtex.parser.records;

import org.bibtex.parser.Category;
import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.*;

public class Article extends Record {

    public Article(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "editor", "title", "journal", "year", "volume", "number", "pages", "month", "note", "key"
        );
        requiredFields = Arrays.asList("author/editor", "title", "journal", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
