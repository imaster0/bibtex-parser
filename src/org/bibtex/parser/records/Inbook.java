package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Inbook extends Record {

    public Inbook(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "editor", "title", "chapter", "pages", "publisher", "year", "volume",
                "number", "series", "type", "address", "edition", "month", "note", "key"
        );
        requiredFields = Arrays.asList("author/editor", "title", "chapter/pages", "publisher", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
