package br.com.fiap.epictask.service;

import br.com.fiap.epictask.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${epictask.jwt.duration}")
    private long duration;

    @Value("${epictask.jwt.secret}")
    private String secret;

    public String createToken(Authentication authenticate) {
        User user = (User) authenticate.getPrincipal();
        Date today = new Date();

        Date expirationDate = new Date(today.getTime() + duration);

        System.out.println(today);
        System.out.println(expirationDate);

        String token = Jwts.builder()
                .setIssuer("EpickTaskAPI")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return token;
    }

    public boolean valid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }
}
