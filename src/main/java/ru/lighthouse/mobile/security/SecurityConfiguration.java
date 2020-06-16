package ru.lighthouse.mobile.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.lighthouse.mobile.security.jwt.JWTSecurityContextRepository;
import ru.lighthouse.mobile.security.jwt.JWTService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static ru.lighthouse.mobile.App.HEALTH_URI;
import static ru.lighthouse.mobile.SwaggerConfig.SWAGGER_URIES;
import static ru.lighthouse.mobile.security.SecurityConfiguration.Role.ROLE_ANDROID;
import static ru.lighthouse.mobile.security.SecurityConfiguration.Role.ROLE_INTEGRATION;
import static ru.lighthouse.mobile.security.SecurityConfiguration.Role.ROLE_IOS;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public interface Role {
        String ROLE_INTEGRATION = "INTEGRATION";
        String ROLE_IOS = "IOS";
        String ROLE_ANDROID = "ANDROID";
        String ROLE_INTEGRATION_WITH_PREFIX = "ROLE_" + ROLE_INTEGRATION;
    }

    // it has to be a Resource or Autowired, either it will be a cycle: SecurityConfiguratio -> UserDetailsService
    @Resource
    private JWTService jwtService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
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
                .anyRequest().hasAnyRole(ROLE_INTEGRATION, ROLE_IOS, ROLE_ANDROID);
    }

    @Override
    public void configure(final WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(SWAGGER_URIES);
    }

    private SecurityContextRepository securityContextRepositoryObject() {
        return new JWTSecurityContextRepository(jwtService);
    }

    private AuthenticationEntryPoint failedAuthenticationEntryPointObject() {
        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}