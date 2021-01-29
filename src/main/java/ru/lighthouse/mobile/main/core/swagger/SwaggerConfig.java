package ru.lighthouse.mobile.main.core.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    private static final String WEB_JARS = "/webjars/**";
    private static final String UI_HTML = "/swagger-ui.html";
    public static final String[] SWAGGER_URIES = new String[]{"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            UI_HTML,
            WEB_JARS};

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.lighthouse.mobile.main.rest.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UI_HTML)
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler(WEB_JARS)
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}