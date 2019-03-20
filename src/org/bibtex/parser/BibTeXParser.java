package org.bibtex.parser;

import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses text to Bibtex object
 */
public class BibTeXParser {
    private static Map<String, String> stringVariables;

    /**
     * Parses String input to BibTeXObject
     * @param input
     * @return BibTeXObject
     * @throws UndefinedRecordException
     */
    public static BibTeXObject parse(String input) throws UndefinedRecordException {
        BibTeXObject result = new BibTeXObject();
        stringVariables = new TreeMap<>();

        Pattern recordPattern = Pattern.compile("(?<=@)[^@]*(?=}).");
        Pattern categoryPattern = Pattern.compile("^[^{]*");
        Pattern keyPattern = Pattern.compile("(?<=[{])(.*),$", Pattern.MULTILINE);
        Pattern attributePattern = Pattern.compile("(^.*) = (.*),", Pattern.MULTILINE);
        Pattern stringPattern = Pattern.compile("([^{=]*)=([^=}]*)");
        Matcher recordMatch = recordPattern.matcher(input);

        while (recordMatch.find()) {
            Matcher category = categoryPattern.matcher(recordMatch.group(0).trim());
            Matcher attributes = attributePattern.matcher(recordMatch.group(0).trim());
            Matcher key = keyPattern.matcher(recordMatch.group(0).trim());

            if (category.find() && key.find()) {
                try {
                    List<Field> fieldsList = new ArrayList<>();
                    while (attributes.find()) {
                        fieldsList.add(new Field(attributes.group(1).trim(), attributes.group(2).trim()));
                    }
                    Record record = RecordFactory.create(Category.valueOf(category.group(0).trim()), key.group(1).trim(), fieldsList);
                    result.addRecord(record.getKey(), record);
                } catch (NullPointerException ex) {
                    System.err.println("- skipped record " + category.group(0).trim());
                } catch (IllegalArgumentException ex) {
                    if (category.group(0).trim().equals("comment") || category.group(0).trim().equals("preamble")) {}
                    else throw new UndefinedRecordException("Unknown field " + category.group(0).trim());
                }
            } else if (category.group(0).trim().equals("String")) {
                Matcher stringValues = stringPattern.matcher(recordMatch.group(0).trim());
                if (stringValues.find()) {
                    addVariable(stringValues.group(1).trim(),
                            stringValues.group(2).trim().substring(1, stringValues.group(2).trim().length() - 1));
                }
            }
        }
        formatFields(result);
        return result;
    }

    /**
     * Returns value of string variable for a key
     * @param key
     * @return String value
     */
    public static String getStringVariable(String key) {
        String result = stringVariables.get(key);
        return (result == null ? key : result);
    }

    /**
     * Adds string variable to the list of variables
     * @param key
     * @param name
     */
    public static void addVariable(String key, String name) {
        stringVariables.put(key, name);
    }

    /**
     * Gets author firstname and surname in proper order
     * @param str
     * @return name
     */
    private static String getAuthor(String str) {
        String[] parts = str.split("\\|");
        parts = Arrays.stream(parts)
                .map(String::trim)
                .toArray(String[]::new);

        if (parts.length == 3) {
            return "\n\t" + parts[1] + " " + parts[2] + " " + parts[0];
        }
        String result = Arrays.stream(str.split("\\|"))
                .reduce((x,y) -> y + " " + x)
                .get();
        return "\n\t" + result.trim();
    }

    /**
     * Formats a field value properly
     * @param field
     * @return
     */
    public static String valueFormat(Field field) {
        String s = field.getValue();
        if (s.equals("")) { return ""; }
        String[] strings = s.split("#");

        String result = Arrays.stream(strings)
                .map(String::trim)
                .map(x -> x.charAt(0) != '"' ? getStringVariable(x) : x.substring(1, x.length() - 1))
                .reduce(String::concat)
                .orElse("");

        //special fields
        switch (field.getName()) {
            case "author":
                String[] authors = result.split("and");
                result = Arrays.stream(authors)
                        .map(String::trim)
                        .map(x -> getAuthor(x))
                        .reduce(String::concat)
                        .orElse("");
                break;
        }

        return result;
    }

    /**
     * Formats all fields properly
     * @param obj
     */
    static void formatFields(BibTeXObject obj) {
        for (Record record : obj.getRecords().values()) {
            for (Map.Entry<String, Field> field : record.getFields()) {
                field.getValue().setValue(valueFormat(field.getValue()));
            }
        }
    }
}
