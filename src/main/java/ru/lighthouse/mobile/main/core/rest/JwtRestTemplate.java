package ru.lighthouse.mobile.main.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.client.RestTemplate;
import ru.lighthouse.mobile.main.boot.security.SecurityRole;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
public class JwtRestTemplate extends RestTemplate {

    private final JWTService jwtService;

    public HttpHeaders createHeadersWithAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(getAuth());
        return headers;
    }

    public HttpHeaders createHeadersWithAuth(LinkedHashMap<String, Object> userDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(getAuth(userDetails));
        return headers;
    }

    public String getAuth() {
        return getAuth(null);
    }

    public String getAuth(LinkedHashMap<String, Object> userDetails) {
        return jwtService.createJWTToken("admin",
                                         AuthorityUtils.createAuthorityList(SecurityRole.INTEGRATION.getSystemName()),
                                         userDetails);
    }
}
