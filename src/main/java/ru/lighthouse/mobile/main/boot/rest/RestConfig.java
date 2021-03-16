package ru.lighthouse.mobile.main.boot.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;
import ru.lighthouse.mobile.main.core.rest.JwtRestTemplate;

@Configuration
public class RestConfig {

    @Value("#{new Boolean('${server.http2.enabled}')}")
    private Boolean http2Enabled;

    @Bean
    public JwtRestTemplate jwtRestTemplate(JWTService jwtService) {
        return http2Enabled ?
                new JwtRestTemplate(jwtService) :
                new JwtRestTemplate(new OkHttp3ClientHttpRequestFactory(), jwtService);
    }

    @Bean
    public RestTemplate restTemplate() {
        return http2Enabled ?
                new RestTemplate(new OkHttp3ClientHttpRequestFactory()) :
                new RestTemplate();
    }
}
