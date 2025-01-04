package com.example.globaldorm;

import com.example.globaldorm.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
@ComponentScan(basePackages = {"com.example.globaldorm.config", "com.example.globaldorm.model", "com.example.globaldorm.repository", "com.example.globaldorm.service", "com.example.globaldorm.controller"})
public class GlobalDormApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlobalDormApplication.class, args);
    }

}
