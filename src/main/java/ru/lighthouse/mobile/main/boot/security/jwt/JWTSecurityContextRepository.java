package ru.lighthouse.mobile.main.boot.security.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JWTSecurityContextRepository implements SecurityContextRepository {
    private final JWTService jwtService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        requestResponseHolder.setResponse(new SaveToAuthorizationHeaderResponseWrapper(response));
        return readContextFromRequestHeader(request);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        SaveToAuthorizationHeaderResponseWrapper responseWrapper = (SaveToAuthorizationHeaderResponseWrapper) response;
        if (!responseWrapper.isContextSaved()) {
            responseWrapper.saveContext(context);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String header = request.getHeader(jwtService.getHeader());
        return header != null && header.startsWith(jwtService.getPrefix());
    }

    private SecurityContext readContextFromRequestHeader(HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        try {
            String header = request.getHeader(jwtService.getHeader());
            String jwtToken = header.replace(jwtService.getPrefix(), "");
            Authentication authentication = jwtService.convertJWTTokenToAuthentication(jwtToken);
            context.setAuthentication(authentication);
        } catch (Exception e) {
            // do nothing
            return context;
        }
        return context;
    }

    private class SaveToAuthorizationHeaderResponseWrapper extends SaveContextOnUpdateOrErrorResponseWrapper {
        private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

        public SaveToAuthorizationHeaderResponseWrapper(HttpServletResponse response) {
            super(response, true);
        }

        @Override
        protected void saveContext(SecurityContext context) {
            HttpServletResponse response = (HttpServletResponse) getResponse();
            Authentication auth = context.getAuthentication();
            if (auth == null || trustResolver.isAnonymous(auth)) {
                return;
            }
            String jwtToken = jwtService.createJWTToken(auth.getName(), auth.getAuthorities(), auth.getDetails());
            response.addHeader(jwtService.getHeader(), jwtToken);
        }
    }
}