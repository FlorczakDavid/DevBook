package co.simplon.devbookapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class Config {

	@Value("${co.simplon.socwork.tousBcrypt}")
	private int tours;
	
	@Value("${co.simplon.socwork.secretJWT}")
	private String secret;
	
	@Value("${co.simplon.socwork.hasExpiration}")
	private boolean hasExpiration;
	
	@Value("${co.simplon.socwork.expirationMinutes}")
	private int expirationMinutes;
	
	@Value("${co.simplon.socwork.issuer}")
	private String issuer;

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Value("${co.simplon.socwork.cors}")
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
				.authorizeHttpRequests((req) -> req
						.requestMatchers(HttpMethod.GET, "/accounts/with-role")
						.hasRole("MANAGER"))
				.authorizeHttpRequests((req) -> req
						.requestMatchers(HttpMethod.POST, "/accounts", "/accounts/login").anonymous())
//				.authorizeHttpRequests((req) -> req
//						.requestMatchers(HttpMethod.GET, "/accounts/get-account").anonymous())
				// Always last rule:
				.authorizeHttpRequests((reqs) -> reqs.anyRequest().authenticated())
				.oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults()))
				.build();
    	
    }
    
}



