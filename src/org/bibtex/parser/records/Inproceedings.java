package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Inproceedings extends Record {

    public Inproceedings(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "booktitle", "year", "editor", "volume",
                "number", "series", "pages", "address", "month", "organization",
                "publisher", "note", "key"
        );
        requiredFields = Arrays.asList("author", "title", "booktitle", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
