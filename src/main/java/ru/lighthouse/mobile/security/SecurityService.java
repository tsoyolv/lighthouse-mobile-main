package ru.lighthouse.mobile.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public final class SecurityService {
    private final JWTService jwtService;

    public Token retrieveToken(SecurityContext context) {
        final UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) context.getAuthentication();
        return new Token((String)authentication.getPrincipal(), authentication.getAuthorities(), (LinkedHashMap<String, Object>) authentication.getDetails());
    }

    @Data @AllArgsConstructor
    public class Token {
        private String phoneNumber;
        private Collection<GrantedAuthority> authorities;
        private LinkedHashMap<String, Object> details;
        public Long getUserId() {
            return Long.valueOf((Integer) details.get(jwtService.getDetailsClaimUserIdClaimName()));
        }
        public Date getBirthDate() {
            final Object o = details.get(jwtService.getDetailsClaimUserBirthDate());
            return o == null ? null : (Date) o;
        }
        public String getFirstName() {
            return String.valueOf(details.get(jwtService.getDetailsClaimUserFirstName()));
        }
        public String getSecondName() {
            return String.valueOf(details.get(jwtService.getDetailsClaimUserSecondName()));
        }
        public String getLastName() {
            return String.valueOf(details.get(jwtService.getDetailsClaimUserLastName()));
        }
    }
}
