package ru.lighthouse.mobile.main.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import ru.lighthouse.mobile.main.core.FileUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Map;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig extends WebMvcConfigurationSupport {
    private static final String WEB_JARS = "/webjars/**";
    private static final String UI_HTML = "/swagger-ui.html";
    public static final String[] SWAGGER_URIES = new String[]{"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            UI_HTML,
            WEB_JARS};

    private final DomainConfig domainConfig;
    private final BuildProperties buildProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.lighthouse.mobile.main.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UI_HTML)
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler(WEB_JARS)
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Документация для MOBILE API Purevision",
                getApiDescription(),
                "VERSION " + buildProperties.getVersion(),
                "Условия использования API",
                new Contact("Oleg Tsoy", "https://github.com/tsoyolv", "oleg.tcoi.work@gmail.com"),
                "Лицензия API", "API license URL", Collections.emptyList());
    }

    private String getApiDescription() {
        StrSubstitutor sub = new StrSubstitutor(
                Map.of(
                        "domainUrl", domainConfig.getUrl(),
                        "otpUri", "/login/otp",
                        "authUri", "/login/auth",
                        "otpParamName", "otp",
                        "phoneNumberParamName", "phoneNumber"),
                "{", "}");
        String description = FileUtils.readAllFileAsString("swagger-apiinfo-description.html");
        return sub.replace(description);
    }
}