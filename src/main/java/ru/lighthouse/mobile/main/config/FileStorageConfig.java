package ru.lighthouse.mobile.main.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FileStorageConfig {

    @Value("${file-storage.images}")
    private String imagesPath;
}