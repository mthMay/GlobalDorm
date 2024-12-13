package com.example.globaldorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.globaldorm.model", "com.example.globaldorm.repository", "com.example.globaldorm.service", "com.example.globaldorm.controller"})
public class GlobalDormApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalDormApplication.class, args);
    }

}
