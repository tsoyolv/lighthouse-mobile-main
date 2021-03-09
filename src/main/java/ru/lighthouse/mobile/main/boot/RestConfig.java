package ru.lighthouse.mobile.main.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;
import ru.lighthouse.mobile.main.core.rest.JwtRestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public JwtRestTemplate jwtRestTemplate(JWTService jwtService) {
        return new JwtRestTemplate(jwtService);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
