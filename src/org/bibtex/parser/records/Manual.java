package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Manual extends Record {

    public Manual(List<Field> fields) {
        List<String> available = Arrays.asList(
                "title", "author", "organization", "address", "edition", "month",
                "year", "note", "key"
        );
        requiredFields = Arrays.asList("title");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
