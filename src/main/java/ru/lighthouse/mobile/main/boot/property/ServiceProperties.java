package ru.lighthouse.mobile.main.boot.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("service")
@Getter
@RequiredArgsConstructor
public class ServiceProperties {

    private final Service crm;

    @ConstructorBinding
    @Getter
    @RequiredArgsConstructor
    public static class Service {
        private final String url;
    }
}
