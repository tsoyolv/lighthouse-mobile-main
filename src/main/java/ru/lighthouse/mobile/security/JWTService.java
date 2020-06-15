package ru.lighthouse.mobile.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class JWTService {
    @Value("${security.jwt.uri}")
    private String authUri;
    @Value("${security.jwt.header:Authorization}")
    private String header;
    @Value("${security.jwt.prefix:Basic }")
    private String prefix;
    @Value("${security.jwt.expiration}")
    private int expiration;
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.claims.authorities.claimName}")
    private String claimAuthoritiesName;
    @Value("${security.jwt.claims.details.claimName}")
    private String claimDetailsName;

    @Value("${security.jwt.claims.details.userId}")
    private String detailsClaimUserIdClaimName;
    @Value("${security.jwt.claims.details.userFirstName}")
    private String detailsClaimUserFirstName;
    @Value("${security.jwt.claims.details.userSecondName}")
    private String detailsClaimUserSecondName;
    @Value("${security.jwt.claims.details.userLastName}")
    private String detailsClaimUserLastName;
    @Value("${security.jwt.claims.details.userBirthDate}")
    private String detailsClaimUserBirthDate;

    public String createJWTToken(String subject, List<String> authorities, Object details) {
        long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(subject)
                .claim(claimAuthoritiesName, authorities)
                .claim(claimDetailsName, details)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, getSecret().getBytes())
                .compact();
        return getPrefix() + token;
    }

    public Authentication convertJWTTokenToAuthentication(String jwtToken) {
        final Claims claims = Jwts.parser().setSigningKey(getSecret().getBytes()).parseClaimsJws(jwtToken).getBody();
        String phoneNumber = claims.getSubject();
        if (phoneNumber == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        final List<String> authoritiesStrings = claims.get(claimAuthoritiesName, List.class);
        List<GrantedAuthority> authorities = authoritiesStrings.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(phoneNumber, null, authorities);
        authToken.setDetails(claims.get(claimDetailsName));
        return authToken;
    }
}
