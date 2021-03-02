package ru.lighthouse.mobile.main.boot.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum SecurityRole {
    ADMIN("ROLE_ADMIN"),
    MOBILE("ROLE_MOBILE"),
    INTEGRATION("ROLE_INTEGRATION");

    public static final String ROLE_INTEGRATION_STR = "ROLE_INTEGRATION";
    public static final String ROLE_ADMIN_STR = "ROLE_ADMIN";

    private final String systemName;
}
