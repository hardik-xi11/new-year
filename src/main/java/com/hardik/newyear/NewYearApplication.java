package com.hardik.newyear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewYearApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewYearApplication.class, args);
    }

}
