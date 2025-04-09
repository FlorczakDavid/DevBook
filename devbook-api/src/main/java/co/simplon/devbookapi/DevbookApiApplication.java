package co.simplon.devbookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class DevbookApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevbookApiApplication.class, args);
    }

}
