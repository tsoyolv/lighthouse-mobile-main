package ru.lighthouse.mobile.main.boot.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("domain")
@ConstructorBinding
@Getter
public class DomainProperties {

    public DomainProperties(String name, String host, String url) {
        this.name = name;
        this.host = host;
        this.url = url;
    }

    private final String name;

    private final String host;

    private final String url;

    @Value("${server.servlet.context-path}")
    private String serviceContextPath;
}
