package ru.lighthouse.mobile.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static ru.lighthouse.mobile.SwaggerConfig.SWAGGER_URIES;
import static ru.lighthouse.mobile.Uri.TEST_SERVICE_URI;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .securityContext().securityContextRepository(securityContextRepositoryObject())
                .and()
                .exceptionHandling().authenticationEntryPoint(failedAuthenticationEntryPointObject())
                .and()
                .authorizeRequests()
                .antMatchers(TEST_SERVICE_URI).permitAll()
                .anyRequest().authenticated();
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