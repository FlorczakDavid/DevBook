package co.simplon.devbookapi.config;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;

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
    this.hasExpiration= hasExpiration;
    this.expirationMinutes = expirationMinutes;
    this.issuer= issuer;
    }

    public String create(String subject) {
    	Instant issuedAt = Instant.now();
    	System.out.println(issuedAt);
    	System.out.println(hasExpiration);

    	//recuprer field authority de Role et mettre dans un array de String
    	
//    	ArrayList<String> rolesList = new ArrayList<>();
//    	roles.forEach(role -> {
//    	String authority = role.getAuthority();
//    	rolesList.add(authority);});
//    	
//    	String[] rolesArray = rolesList.toArray(new String[0]);
    	
    	if(hasExpiration) {
    		System.out.println("called 2nd ");
    		//without IssuedAt
    		//Builder builder = JWT.create().withSubject(subject).withExpiresAt(OffsetDateTime.now().plusMinutes(expirationMinutes).toInstant());
    		
    		//with IssuedAt
			Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject).withExpiresAt(OffsetDateTime.now().plusMinutes(1).toInstant())
					.withIssuer(issuer);
//					.withArrayClaim("roles", rolesArray);
			return builder.sign(algorithm);
		}else {
			Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject);
			return builder.sign(algorithm);
		}
    
    }

}
