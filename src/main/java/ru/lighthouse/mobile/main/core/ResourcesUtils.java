package ru.lighthouse.mobile.main.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public final class ResourcesUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ResourcesUtils() {
    }

    public static <T> T readResourceAsJsonObject(String fileName, Class<T> clazz) {
        try {
            return objectMapper.readValue(FileUtils.class.getClassLoader().getResourceAsStream(fileName), clazz);
        } catch (IOException e) {
            return null;
        }
    }

    public static Path convertResourcePath(String resourcePath) {
        try {
            return Path.of(FileUtils.class.getClassLoader().getResource(resourcePath).toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static String readResourceAsString(String fileName) {
        InputStream resourceAsStream = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
        return convertStreamToString(resourceAsStream);
    }

    private static String convertStreamToString(InputStream is) {
        StringBuilder strBuilder = new StringBuilder("");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }
        } catch (IOException e) {
            return null;
        }
        return strBuilder.toString();
    }
}
