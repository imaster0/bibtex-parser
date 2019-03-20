package org.bibtex.parser.models;

import java.util.Objects;

/**
 * Field in record
 */
public class Field {
    private String name;
    private String value;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static Field emptyField() {
        return new Field("", "");
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(name, field.name) &&
                Objects.equals(value, field.value);
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
