package org.bibtex.parser.models;

import org.bibtex.parser.Category;
import org.bibtex.parser.MissingFieldException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Record class
 */
public class Record {
    private String key;
    private Category category;
    protected Map<String, Field> availableFields;
    protected List<String> requiredFields;

    public Record() {
        availableFields = new HashMap<>();
    }

    /**
     * @return
     * @throws MissingFieldException
     */
    public boolean validate() throws MissingFieldException {
        for (String required : requiredFields) {
            String keys[] = required.split("/");
            boolean missingField = true;
            for (String k : keys) {
                missingField = missingField && (availableFields.get(k).equals(Field.emptyField()));
            }
            if (missingField) {
                throw new MissingFieldException(category, key, required + " is missing");
            }
        }
        return true;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void addField(String key, Field field) {
        if (availableFields.containsKey(key)) {
            availableFields.replace(key, field);
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Map.Entry<String, Field>> getFields() {
        return availableFields.entrySet();
    }

    public Field getField(String name) {
        return availableFields.get(name);
    }

    public boolean hasField(String name) {
        return availableFields.get(name) != null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(category).append(" (").append(key).append(")\n");
        availableFields.values().stream()
                .filter(x -> !x.getValue().equals(""))
                .forEach(x -> str.append("\t").append(x).append("\n"));
        return str.toString();
    }
}
