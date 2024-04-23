package io.shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileTokenizer {

    public static List<String> loadFileToStrings(File file) {
        List<String> lines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("SEVERE ERROR: UNABLE TO LOAD " + file);
        }

        return lines;
    }

    public static Map<String, List<String>> tokenizeLines(String sectionHeading, List<String> lines) {
        Map<String, List<String>> result = new HashMap<>();
        String currentToken = null;
        for (String line : lines) {
            if (line.startsWith(sectionHeading)) {
                currentToken = line.substring(sectionHeading.length() + 1); //We add one to cover a space or separator.
                result.put(currentToken, new LinkedList<>());
            } else if (!line.trim().isEmpty()) {
                result.get(currentToken).add(line);
            }
        }

        return result;
    }
}
