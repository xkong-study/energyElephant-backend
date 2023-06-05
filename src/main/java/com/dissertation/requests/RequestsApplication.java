package com.dissertation.requests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RequestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestsApplication.class, args);
    }

}
