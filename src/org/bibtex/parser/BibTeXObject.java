package org.bibtex.parser;

import org.bibtex.parser.models.Field;
import org.bibtex.parser.models.Record;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Bibtex object representation
 */
public class BibTeXObject {
    private Map<String, Record> records;

    public BibTeXObject() {
        records = new TreeMap<>();
    }

    public List<Record> getAll() {
        return new ArrayList<>(records.values());
    }

    /**
     * Adds new record with key
     * @param key
     * @param record
     */
    public void addRecord(String key, Record record) {
        records.put(key, record);
    }

    public void print(List<Record> r) {
        for(Record record : r) {
            System.out.println(record);
        }
    }

    /**
     * Gets all records
     * @return
     */
    public Map<String, Record> getRecords() {
        return records;
    }

    /**
     * Searches by field name and by params
     * @param key
     * @param params
     * @return
     */
    public List<Record> searchBy(String key, String ...params) {
        List<Record> result = new ArrayList<>();
        for (String param : params) {
            if (key.equals("category")) {
                records.values().stream()
                        .filter(x -> x.getCategory().toString().equals(param))
                        .forEach(result::add);
            } else {
                records.values().stream()
                        .filter(x -> x.hasField(key))
                        .filter(x -> x.getField(key).getValue().contains(param))
                        .forEach(result::add);
            }
        }
        return result;
    }
}
