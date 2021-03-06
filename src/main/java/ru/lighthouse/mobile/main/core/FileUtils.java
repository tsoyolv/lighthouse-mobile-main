package ru.lighthouse.mobile.main.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public final class FileUtils {
    private FileUtils() {
    }

    public static void splitFileByLineNumbers(Path path, int lineNumbers) throws IOException {
        int lineNumbersTemp = lineNumbers;
        int filesCount = 1;

        BufferedReader reader = Files.newBufferedReader(path);
        String line;
        String fileName = path.getFileName().toString();

        final String fileParent = path.getParent().toString() + File.separator;
        final String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
        final String extension = fileName.substring(fileName.lastIndexOf('.'));
        Path newPath = Paths.get(fileParent + fileNameWithoutExtension + filesCount++ + extension);
        while ((line = reader.readLine()) != null) {
            if (lineNumbersTemp-- <= 0) {
                newPath = Paths.get(fileParent + fileNameWithoutExtension + filesCount++ + extension);
                lineNumbersTemp = lineNumbers - 1;
            }
            Files.writeString(newPath, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.writeString(newPath, System.getProperty("line.separator"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        reader.close();
    }
}
