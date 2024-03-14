package io.github.untalsanders.contacts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApiApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsApiApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ContactsApiApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        LOGGER.info("\uD83D\uDE80 ContactsAPI Application is running at http://localhost:9090 \uD83D\uDE80");
    }
}
