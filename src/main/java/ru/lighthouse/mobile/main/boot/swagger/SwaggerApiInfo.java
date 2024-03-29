package ru.lighthouse.mobile.main.boot.swagger;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SwaggerApiInfo {
    private String title;
    private String termsUrl;
    private Contact contact;
    private License license;
    private Description description;

    @Getter
    @Setter
    public static class Contact {
        private String name;
        private String email;
        private String url;
    }

    @Getter
    @Setter
    public static class License {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class Description {
        private String html;
        private Map<String, String> htmlReplaces;
    }
}
