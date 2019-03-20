package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Unpublished extends Record {

    public Unpublished(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "month", "year", "key"
        );
        requiredFields = Arrays.asList("author", "title", "note");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
