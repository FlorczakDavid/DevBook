package co.simplon.devbookapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Config {



    public static String hashEncoder(String password){
        return new BCryptPasswordEncoder(12).encode(password);
    };

    public static boolean verifyPassword(String enteredPassword, String storedHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedHash);
    }

}
