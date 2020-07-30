package ru.lighthouse.mobile.main.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.lighthouse.mobile.main.security.jwt.JWTSecurityContextRepository;
import ru.lighthouse.mobile.main.security.jwt.JWTService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static ru.lighthouse.mobile.main.App.HEALTH_URI;
import static ru.lighthouse.mobile.main.SwaggerConfig.SWAGGER_URIES;
import static ru.lighthouse.mobile.main.security.SecurityConfiguration.Role.ROLE_ANDROID;
import static ru.lighthouse.mobile.main.security.SecurityConfiguration.Role.ROLE_INTEGRATION;
import static ru.lighthouse.mobile.main.security.SecurityConfiguration.Role.ROLE_IOS;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public interface Role {
        String ROLE_INTEGRATION = "INTEGRATION";
        String ROLE_IOS = "IOS_USER";
        String ROLE_ANDROID = "ANDROID_USER";
        String ROLE_INTEGRATION_WITH_PREFIX = "ROLE_" + ROLE_INTEGRATION;
    }

    // it has to be a Resource or Autowired, either it will be a cycle: SecurityConfiguratio -> UserDetailsService
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
                .antMatchers(HEALTH_URI).permitAll()
                .anyRequest().hasAnyRole(ROLE_INTEGRATION, "SUPER_ADMIN",ROLE_IOS, ROLE_ANDROID);
    }

    @Override
    public void configure(final WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(SWAGGER_URIES);
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