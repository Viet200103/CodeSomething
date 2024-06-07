package com.example.contact.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ContactApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactApiApplication.class, args);
    }

}
