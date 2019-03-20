package org.bibtex.parser;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BibTeXFileReader fileReader = new BibTeXFileReader();
        String test;
        if (args.length == 0) {
            System.out.println("Usage: bibtex-parse <path> [category | author <params>]");
        }
        
        try { //"C:/Users/Jakub/Desktop/studia/projekty/po/src/org/bibtex/parser/files/test2.txt"
            test = fileReader.readFile(args[0]);
            BibTeXObject obj = BibTeXParser.parse(test);
            if (args.length == 1) {
                obj.print(obj.getAll());
            } else {
                obj.print(obj.searchBy(args[1], Arrays.copyOfRange(args, 2, args.length)));
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (UndefinedRecordException e) {
            e.printStackTrace();
        }
    }
}
