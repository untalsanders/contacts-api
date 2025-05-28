package io.github.untalsanders.contacts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ContactsApiApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ContactsApiApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
