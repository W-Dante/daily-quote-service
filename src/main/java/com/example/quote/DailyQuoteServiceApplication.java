package com.example.quote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyQuoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyQuoteServiceApplication.class, args);
    }
}
