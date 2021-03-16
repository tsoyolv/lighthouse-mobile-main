package ru.lighthouse.mobile.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConfigurationPropertiesScan("ru.lighthouse.mobile.main.boot.property")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class App {
    public final static String HEALTH_CHECK_URI = "/health-check";
    public final static String HEALTH_RESPONSE = "I AM MOBILE! I AM FINE!";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping(HEALTH_CHECK_URI)
    public String healthCheck() {
        return HEALTH_RESPONSE;
    }
}