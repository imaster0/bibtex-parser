package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Techreport extends Record {

    public Techreport(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "institution", "year", "editor", "volume",
                "number", "series", "address", "month", "organization",
                "publisher", "key", "note"
        );
        requiredFields = Arrays.asList("author", "title", "institution", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
