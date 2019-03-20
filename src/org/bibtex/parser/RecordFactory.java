package org.bibtex.parser;

import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;
import org.bibtex.parser.records.*;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Factory Pattern - creates record by category
 */
public class RecordFactory {
    public static String firstToUpper(String s) {
        return s.charAt(0) + s.substring(1).toLowerCase();
    }

    public static Record create(Category categoryName, String key, List<Field> fields)  {
        try {
            Class cls = Class.forName("org.bibtex.parser.records." + firstToUpper(categoryName.toString()));
            Constructor ct = cls.getConstructor(List.class);
            Record record = (Record) ct.newInstance(fields);
            record.setCategory(categoryName);
            record.setKey(key);
            record.validate();
            return record;
        } catch (MissingFieldException ex) {
            System.err.println(ex.getCategory() + " (" + ex.getKey() + ") " + ex.getMessage());
        } catch (NoSuchMethodException ex) {
            System.err.println("Undefined record type: " + firstToUpper(categoryName.toString()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.getStackTrace();
        }
        return null;
    }
}
