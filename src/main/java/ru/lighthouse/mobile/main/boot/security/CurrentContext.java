package ru.lighthouse.mobile.main.boot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;

import java.util.Date;
import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
public final class CurrentContext {
    private final JWTService jwtService;

    public Long getUserId() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        LinkedHashMap<String, Object> details = getAuthDetails(authentication);
        return Long.valueOf((Integer) details.get(jwtService.getClaimDetailsUserId()));
    }

    public Date getBirthDate() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        LinkedHashMap<String, Object> details = getAuthDetails(authentication);
        final Object o = details.get(jwtService.getClaimDetailsUserBirthDate());
        return o == null ? null : (Date) o;
    }

    public String getFirstName() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        LinkedHashMap<String, Object> details = getAuthDetails(authentication);
        return String.valueOf(details.get(jwtService.getClaimDetailsUserFirstName()));
    }

    public String getSecondName() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        LinkedHashMap<String, Object> details = getAuthDetails(authentication);
        return String.valueOf(details.get(jwtService.getClaimDetailsUserSecondName()));
    }

    public String getLastName() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        LinkedHashMap<String, Object> details = getAuthDetails(authentication);
        return String.valueOf(details.get(jwtService.getClaimDetailsUserLastName()));
    }

    private LinkedHashMap<String, Object> getAuthDetails(Authentication authentication) {
        @SuppressWarnings("unchecked")
        final LinkedHashMap<String, Object> details = (LinkedHashMap<String, Object>) authentication.getDetails();
        return details;
    }
}
