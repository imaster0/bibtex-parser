package org.bibtex.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads file and converts to String
 */
public class BibTeXFileReader {
    /**
     * Reads from file
     * @param filePath
     * @return String
     * @throws IOException
     */
    public String readFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder result = new StringBuilder();
        String textLine = bufferedReader.readLine();
        do {
            result.append(textLine + "\n");
            textLine = bufferedReader.readLine();
        } while (textLine != null);

        bufferedReader.close();
        return result.toString();
    }
}
