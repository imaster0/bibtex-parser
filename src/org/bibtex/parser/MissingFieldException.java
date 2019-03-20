package org.bibtex.parser;

public class MissingFieldException extends Exception {
    private Category category;
    private String key;
    private String message;

    public MissingFieldException(Category category, String key, String message) {
        this.category = category;
        this.key = key;
        this.message = message;
    }

    public String getKey() { return key; }

    public Category getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }
}
