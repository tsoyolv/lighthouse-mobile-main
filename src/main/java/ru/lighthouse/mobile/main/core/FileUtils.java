package ru.lighthouse.mobile.main.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtils {

    public static String readAllFileAsString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/" + fileName)));
        } catch (IOException e) {
            return null;
        }
    }
}
