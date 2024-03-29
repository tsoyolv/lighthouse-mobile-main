package ru.lighthouse.mobile.main.boot.security;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTSecurityContextRepository;
import ru.lighthouse.mobile.main.boot.security.jwt.JWTService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static ru.lighthouse.mobile.main.App.HEALTH_CHECK_URI;
import static ru.lighthouse.mobile.main.boot.security.SecurityRole.ADMIN;
import static ru.lighthouse.mobile.main.boot.security.SecurityRole.INTEGRATION;
import static ru.lighthouse.mobile.main.boot.security.SecurityRole.MOBILE;
import static ru.lighthouse.mobile.main.boot.swagger.SwaggerConfig.SWAGGER_URIES;
import static ru.lighthouse.mobile.main.rest.controller.ImageController.IMAGES_URI;


@ConditionalOnExpression("${security.enabled}")
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // it has to be a Resource or Autowired, either it will be a cycle: SecurityConfiguration -> UserDetailsService
    @Resource
    private JWTService jwtService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.cors().and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .sessionManagement().disable()
                .securityContext().securityContextRepository(securityContextRepositoryObject())
                .and()
                .exceptionHandling().authenticationEntryPoint(failedAuthenticationEntryPointObject())
                .and()
                .authorizeRequests()
                .antMatchers(SWAGGER_URIES).permitAll()
                .antMatchers(HEALTH_CHECK_URI, IMAGES_URI + "/**").permitAll()
                .anyRequest().hasAnyRole(INTEGRATION.name(), ADMIN.name(), MOBILE.name());
    }

/*
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
*/

    private SecurityContextRepository securityContextRepositoryObject() {
        return new JWTSecurityContextRepository(jwtService);
    }

    private AuthenticationEntryPoint failedAuthenticationEntryPointObject() {
        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}