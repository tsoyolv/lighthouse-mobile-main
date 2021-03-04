package ru.lighthouse.mobile.main.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.lighthouse.mobile.main.core.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TestResourcesUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private TestResourcesUtils() {}

    public static <T> T readResourceAsJsonObject(String fileName, Class<T> clazz) {
        try {
            return objectMapper.readValue(FileUtils.class.getClassLoader().getResourceAsStream(fileName), clazz);
        } catch (IOException e) {
            return null;
        }
    }

    public static Path convertResourcePath(String resourcePath) throws URISyntaxException {
        return Path.of(TestResourcesUtils.class.getClassLoader().getResource(resourcePath).toURI());
    }

    public static String readResourceAsString(String resourcePath) throws URISyntaxException, IOException {
        URL resource = TestResourcesUtils.class.getClassLoader().getResource(resourcePath);
        return Files.readString(Paths.get(resource.toURI()));
    }
}
