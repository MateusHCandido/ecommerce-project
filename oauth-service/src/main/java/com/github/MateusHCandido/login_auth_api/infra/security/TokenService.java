package com.github.MateusHCandido.login_auth_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.MateusHCandido.login_auth_api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("oauth-service")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationTime())
                    .sign(algorithm);

        } catch (JWTCreationException exception) { throw new RuntimeException("Error while authenticating"); }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("oauth-service")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException verificationException) { return null; }
    }

    protected Instant generateExpirationTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }
}
