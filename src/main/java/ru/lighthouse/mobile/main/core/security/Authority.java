package ru.lighthouse.mobile.main.core.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum Authority {
    SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ADMIN("ROLE_ADMIN"),
    IOS_USER("ROLE_IOS_USER"),
    ANDROID_USER("ROLE_ANDROID_USER"),
    SUPER_AGENT("ROLE_SUPER_AGENT"),
    AGENT("ROLE_AGENT"),
    INTEGRATION("ROLE_INTEGRATION");

    public static final String ROLE_INTEGRATION = "ROLE_INTEGRATION";
    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SUPER_AGENT = "ROLE_SUPER_AGENT";
    public static final String ROLE_AGENT = "ROLE_AGENT";

    private final String systemName;
}
