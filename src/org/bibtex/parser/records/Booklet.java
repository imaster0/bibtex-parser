package org.bibtex.parser.records;

import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.Arrays;
import java.util.List;

public class Booklet extends Record {

    public Booklet(List<Field> fields) {
        List<String> available = Arrays.asList(
                "title", "author", "howpublished", "address", "month", "year", "note", "key"
        );
        requiredFields = Arrays.asList("title");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
