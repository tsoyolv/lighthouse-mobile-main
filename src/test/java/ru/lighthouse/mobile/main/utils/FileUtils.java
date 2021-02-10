package ru.lighthouse.mobile.main.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtils {

    public static String readFileAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + file)));
        } catch (IOException e) {
            return null;
        }
    }
}
