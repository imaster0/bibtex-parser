package org.bibtex.parser.records;

import org.bibtex.parser.Category;
import org.bibtex.parser.MissingFieldException;
import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Book extends Record {

    public Book(List<Field> fields) {
        List<String> available = Arrays.asList(
                "author", "title", "publisher", "year", "volume", "series", "address", "edition", "month", "note", "key"
        );
        requiredFields = Arrays.asList("author", "title", "publisher", "year");
        available.forEach(x -> availableFields.put(x, Field.emptyField()));
        fields.forEach(field -> addField(field.getName(), field));
    }
}
