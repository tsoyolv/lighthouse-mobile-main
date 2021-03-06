package ru.lighthouse.mobile.main.boot.security.jwt;

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
import org.springframework.util.StringUtils;

import java.util.Collection;
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
    private long expiration;
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.claims.authorities.claimName}")
    private String claimAuthoritiesName;
    @Value("${security.jwt.claims.details.claimName}")
    private String claimDetailsName;

    @Value("${security.jwt.claims.details.userId}")
    private String claimDetailsUserId;
    @Value("${security.jwt.claims.details.userFirstName}")
    private String claimDetailsUserFirstName;
    @Value("${security.jwt.claims.details.userSecondName}")
    private String claimDetailsUserSecondName;
    @Value("${security.jwt.claims.details.userLastName}")
    private String claimDetailsUserLastName;
    @Value("${security.jwt.claims.details.userBirthDate}")
    private String claimDetailsUserBirthDate;

    public String createJWTToken(String subject, Collection<? extends GrantedAuthority> grantedAuthorities, Object details) {
        List<String> authorities = grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
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
        final String subject = claims.getSubject();
        if (StringUtils.isEmpty(subject)) {
            return null;
        }
        @SuppressWarnings("unchecked") final List<GrantedAuthority> grantedAuthorities = ((List<String>) claims.get(claimAuthoritiesName)).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        final Object details = claims.get(claimDetailsName);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(subject, null, grantedAuthorities);
        authToken.setDetails(details);
        return authToken;
    }
}
