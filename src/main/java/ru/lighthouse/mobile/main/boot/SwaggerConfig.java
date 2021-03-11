package ru.lighthouse.mobile.main.boot;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import ru.lighthouse.mobile.main.boot.swagger.SwaggerApiInfo;
import ru.lighthouse.mobile.main.core.resource.ResourcesUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.Map;

@ConditionalOnExpression("${swagger.enabled}")
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    private static final String WEB_JARS = "/webjars/**";
    public static final String UI_HTML = "swagger-ui.html";
    public static final String UI_HTML_URI = "/" + UI_HTML;
    public static final String URI = "/swagger";
    public static final String[] SWAGGER_URIES = new String[]{"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            UI_HTML_URI,
            URI,
            WEB_JARS};

    private final BuildProperties buildProperties;
    private final SwaggerApiInfo apiInfo;
    private final ServletContext servletContext;
    private final Boolean http2Enabled;

    public SwaggerConfig(BuildProperties buildProperties,
                         @Value("${swagger.config-locations.api-info}") String apiInfoPath,
                         @Value("${swagger.config-locations.api-info-description}") String apiInfoDescriptionPath,
                         ServletContext servletContext,
                         @Value("#{new Boolean('${server.http2.enabled}')}") Boolean http2Enabled) {
        this.buildProperties = buildProperties;
        this.apiInfo = ResourcesUtils.readResourceAsJsonObject(apiInfoPath, SwaggerApiInfo.class);
        this.servletContext = servletContext;
        this.apiInfo.getDescription().setHtml(apiInfoDescriptionPath);
        this.http2Enabled = http2Enabled;
    }

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
        registry.addResourceHandler(UI_HTML_URI)
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler(WEB_JARS)
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                apiInfo.getTitle(),
                getApiDescription(apiInfo.getDescription()),
                "VERSION " + buildProperties.getVersion(),
                apiInfo.getTermsUrl(),
                new Contact(apiInfo.getContact().getName(), apiInfo.getContact().getUrl(), apiInfo.getContact().getEmail()),
                apiInfo.getLicense().getName(), apiInfo.getLicense().getUrl(), Collections.emptyList());
    }

    private String getApiDescription(SwaggerApiInfo.Description description) {
        Map<String, String> htmlReplaces = description.getHtmlReplaces();
        htmlReplaces.put("server", servletContext.getServerInfo());
        htmlReplaces.put("httpVersion", http2Enabled ? "HTTP/2" : "HTTP/1.1");
        StrSubstitutor sub = new StrSubstitutor(htmlReplaces, "{", "}");
        String descriptionInfo = ResourcesUtils.readResourceAsString(description.getHtml());
        return sub.replace(descriptionInfo);
    }
}