package ru.lighthouse.mobile.main.boot.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("file-storage")
@ConstructorBinding
@Getter
@AllArgsConstructor
public class FileStorageProperties {

    private String imagesPath;
}
