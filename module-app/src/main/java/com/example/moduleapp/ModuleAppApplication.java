package com.example.moduleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.moduleapp", "com.example.moduleservice"})
public class ModuleAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleAppApplication.class, args);
    }

}
