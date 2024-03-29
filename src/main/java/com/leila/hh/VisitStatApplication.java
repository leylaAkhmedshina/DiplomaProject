package com.leila.hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class VisitStatApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisitStatApplication.class, args);
    }

}
