package ru.lighthouse.mobile.main.core.resource;

import java.io.IOException;

public class ResourceNotFoundException extends RuntimeException {
    private static final String RESOURCE_NOT_FOUND = "Resource not found";
    private static final String RESOURCE_NOT_FOUND_WITH_PATH = RESOURCE_NOT_FOUND + ": ";

    public ResourceNotFoundException(String resource) {
        super(RESOURCE_NOT_FOUND_WITH_PATH + resource);
    }

    public ResourceNotFoundException(String resource, Throwable cause) {
        super(RESOURCE_NOT_FOUND_WITH_PATH + resource, cause);
    }

    public ResourceNotFoundException(IOException ioException) {
        super(RESOURCE_NOT_FOUND, ioException);
    }
}
