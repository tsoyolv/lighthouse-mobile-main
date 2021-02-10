package ru.lighthouse.mobile.main.core.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtils {

    public static String readAllFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/" + file)));
    }
}
