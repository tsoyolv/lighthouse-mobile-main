package ru.lighthouse.mobile.main.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/" + file)));
    }
}
