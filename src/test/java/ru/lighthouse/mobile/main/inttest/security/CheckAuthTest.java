package ru.lighthouse.mobile.main.inttest.security;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;
import ru.lighthouse.mobile.main.inttest.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.lighthouse.mobile.main.boot.security.SecurityRole.ADMIN;

public class CheckAuthTest extends AbstractIntegrationTest {

    @Autowired
    private JWTService jwtService;

    @Test
    public void testUnauthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(CheckAuthController.CHECK_AUTH_URI)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testAuthorized() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = createJwtToken();
        httpHeaders.add(jwtService.getHeader(), token);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(CheckAuthController.CHECK_AUTH_URI).headers(httpHeaders);
        mvc.perform(requestBuilder)
           .andExpect(status().isOk())
           .andExpect(content().string(CheckAuthController.AUTH_CHECK));
    }

    protected String createJwtToken() {
        return jwtService.createJWTToken(DEFAULT_PHONE_NUMBER, AuthorityUtils.createAuthorityList(ADMIN.getSystemName()), null);
    }
}