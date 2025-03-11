package com.example.moduleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.modulecore.domain")  // module-core의 엔티티 패키지 등록
@ComponentScan(basePackages = {"com.example.moduleapp", "com.example.moduleservice"})
public class ModuleAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleAppApplication.class, args);
    }

}
