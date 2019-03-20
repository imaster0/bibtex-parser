package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Incollection extends Record {

    public Incollection(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "booktitle", "publisher", "year", "editor", "volume", "number",
                "series", "type", "chapter", "pages", "address", "address", "edition", "month", "note", "key"
        );
        requiredFields = Arrays.asList("author", "title", "booktitle", "publisher", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
