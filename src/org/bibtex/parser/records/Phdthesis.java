package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Phdthesis extends Record {

    public Phdthesis(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "school", "year", "type", "address",
                "month", "note", "key"
        );
        requiredFields = Arrays.asList("author", "title", "school", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
