package co.simplon.devbookapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class Config {

	@Value("${co.simplon.devbook.tousBcrypt}")
	private int tours;
	
	@Value("${co.simplon.devbook.secretJWT}")
	private String secret;
	
	@Value("${co.simplon.devbook.hasExpiration}")
	private boolean hasExpiration;
	
	@Value("${co.simplon.devbook.expirationMinutes}")
	private int expirationMinutes;
	
	@Value("${co.simplon.devbook.issuer}")
	private String issuer;

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Value("${co.simplon.devbook.cors}")
			private String origins;
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("POST", "GET", "PATCH", "PUT", "DELETE").allowedOrigins(origins);
			}
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(tours);
	}
	
    @Bean
    JwtProvider jwtProvider() {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return new JwtProvider(algorithm, hasExpiration,expirationMinutes, issuer);
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
    	SecretKey secretKey = new SecretKeySpec(secret.getBytes(),
        "HMACSHA256");
 
    OAuth2TokenValidator<Jwt> validators = JwtValidators.createDefaultWithIssuer(issuer);
    
    NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey)
        .macAlgorithm(MacAlgorithm.HS256)
        .build();
    decoder.setJwtValidator(validators);
    
    return decoder;
    }
    
    @Bean 
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	return http.cors(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())
//				.authorizeHttpRequests((req) -> req
//						.requestMatchers(HttpMethod.GET, "/accounts/with-role")
//						.hasRole("MEMBER"))
				.authorizeHttpRequests((req) -> req
						.requestMatchers(HttpMethod.POST, "/accounts", "/accounts/authenticate").anonymous())
//				.authorizeHttpRequests((req) -> req
//						.requestMatchers(HttpMethod.GET, "/accounts/get-account").anonymous())
				// Always last rule:
				.authorizeHttpRequests((reqs) -> reqs.anyRequest().authenticated())
				.oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults()))
				.build();
    	
    }
    
}



