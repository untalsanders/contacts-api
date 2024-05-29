package io.github.untalsanders.contacts;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApiApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ContactsApiApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
