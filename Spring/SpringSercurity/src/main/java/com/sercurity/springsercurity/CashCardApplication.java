package com.sercurity.springsercurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The application entry point
 *
 * @author Felipe Gutierrez
 * @author Josh Cummings
 */
@EnableMethodSecurity
@SpringBootApplication
public class CashCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashCardApplication.class, args);
    }

    @Bean
    SecurityFilterChain appSecurity(
            HttpSecurity http,
            ProblemDetailsAuthenticationEntryPoint entryPoint
    ) throws Exception {
        http.authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(HttpMethod.GET, "/cashcards/**")
                                .hasAuthority("SCOPE_cashcard:read")
                                .requestMatchers("/cashcards/**")
                                .hasAuthority("SCOPE_cashcard:write")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .authenticationEntryPoint(entryPoint)
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}
