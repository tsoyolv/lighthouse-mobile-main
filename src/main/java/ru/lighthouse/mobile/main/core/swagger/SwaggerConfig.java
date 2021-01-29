package ru.lighthouse.mobile.main.core.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import ru.lighthouse.mobile.main.core.domain.DomainParams;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

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

    private final DomainParams domainParams;

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
                "Закрытое API для работы с MOBILE API Purevision." +
                        "\nДля каждого запроса требуется TOKEN, который можно получить при авторизации в Purevision." +
                        "\n\nДля авторизации в приложение используется СМС-аутентификация.\n" +
                        "I. Для запроса кода OTP используется:\n" +
                        "    POST method\n" +
                        "    [" + domainParams.getUrl() + "/login/otp](" + domainParams.getUrl() + "/login/otp)\n" +
                        "    Параметры: \"phoneNumber\"\n" +
                        "    Ответы:\n" +
                        "        1. 200 OK - смс отослали\n" +
                        "        2. 422 PHONE_NUMBER_INVALID -неверный формат телефона, должен быть <7xxxxxxxxxx>\n" +
                        "II. Для авторизации:\n" +
                        "    POST method\n" +
                        "    [" + domainParams.getUrl() + "/login/auth](" + domainParams.getUrl() + "/login/auth)\n" +
                        "    Параметры: \"phoneNumber\", \"otp\"\n" +
                        "\nНа каждый запрос будет отсылаться обновленный TOKEN, можно использовать и старый, но он протухает через установленное время на сервере.",
                "BETA версия",
                "Условия использования API",
                new Contact("Oleg Tsoy", "https://github.com/tsoyolv", "tsoyolv@gmail.com"),
                "Лицензия API", "API license URL", Collections.emptyList());
    }
}