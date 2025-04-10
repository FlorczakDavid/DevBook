package co.simplon.devbookapi.config;

import java.time.Instant;
import java.time.OffsetDateTime;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtProvider {

    private final Algorithm algorithm;

    private final boolean hasExpiration;
    private final int expirationMinutes;
    private final String issuer;

    JwtProvider(Algorithm algorithm, boolean hasExpiration, int expirationMinutes, String issuer) {
        this.algorithm = algorithm;
        this.hasExpiration = hasExpiration;
        this.expirationMinutes = expirationMinutes;
        this.issuer = issuer;
    }

    public String create(String subject) {
        Instant issuedAt = Instant.now();
        System.out.println(issuedAt);
        System.out.println(hasExpiration);


        if (hasExpiration) {
            System.out.println("called 2nd ");

            Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject).withExpiresAt(OffsetDateTime.now().plusMinutes(1).toInstant())
                    .withIssuer(issuer);

            return builder.sign(algorithm);
        } else {
            Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject);
            return builder.sign(algorithm);
        }

    }

}
