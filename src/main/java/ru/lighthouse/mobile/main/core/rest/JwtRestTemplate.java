package ru.lighthouse.mobile.main.core.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import ru.lighthouse.mobile.main.boot.security.SecurityRole;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;

public class JwtRestTemplate extends RestTemplate {

    private final JWTService jwtService;

    public JwtRestTemplate(ClientHttpRequestFactory httpRequestFactory, JWTService jwtService) {
        super(httpRequestFactory);
        this.jwtService = jwtService;
    }

    public JwtRestTemplate(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public <T> RequestCallback httpEntityCallback(Object requestBody) {
        if (requestBody instanceof HttpEntity) {
            HttpEntity<?> requestBodyWithAuth = requestBodyWithAuthHeader((HttpEntity<?>) requestBody);
            return super.httpEntityCallback(requestBodyWithAuth);
        }
        return super.httpEntityCallback(requestBody);
    }

    @Override
    public <T> RequestCallback httpEntityCallback(Object requestBody, Type responseType) {
        if (requestBody instanceof HttpEntity) {
            HttpEntity<?> requestBodyWithAuth = requestBodyWithAuthHeader((HttpEntity<?>) requestBody);
            return super.httpEntityCallback(requestBodyWithAuth, responseType);
        }
        return super.httpEntityCallback(requestBody, responseType);
    }

    private HttpEntity<?> requestBodyWithAuthHeader(HttpEntity<?> requestBody) {
        HttpHeaders newHeaders = new HttpHeaders();
        HttpHeaders headers = requestBody.getHeaders();
        List<String> auth = headers.get(HttpHeaders.AUTHORIZATION);
        if (auth == null || auth.isEmpty()) {
            newHeaders.putAll(headers);
            newHeaders.set(HttpHeaders.AUTHORIZATION, createAuthHeader());
            return new HttpEntity<>(requestBody.getBody(), newHeaders);
        }
        return requestBody;
    }

    public HttpHeaders createHeadersWithAuth(LinkedHashMap<String, Object> userDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, createAuthHeader(userDetails));
        return headers;
    }

    public String createAuthHeader() {
        return createAuthHeader(null);
    }

    public String createAuthHeader(LinkedHashMap<String, Object> userDetails) {
        return jwtService.createJWTToken("admin",
                                         AuthorityUtils.createAuthorityList(SecurityRole.INTEGRATION.getSystemName()),
                                         userDetails);
    }
}
