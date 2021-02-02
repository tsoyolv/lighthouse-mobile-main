package ru.lighthouse.mobile.main.core.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DomainConfig {

    @Value("${domain.name}")
    private String name;

    @Value("${domain.host}")
    private String host;

    @Value("${domain.url}")
    private String url;

    @Value("${server.servlet.context-path}")
    private String serverContextPath;
}
