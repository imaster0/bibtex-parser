package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Misc extends Record {

    public Misc(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "howpublished", "month", "year", "note", "key"
        );
        requiredFields = new ArrayList<>();
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
