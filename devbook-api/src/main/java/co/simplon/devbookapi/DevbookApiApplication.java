package co.simplon.devbookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DevbookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevbookApiApplication.class, args);
    }

}
