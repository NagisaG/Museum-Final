package com.museum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.museum.*")
@SpringBootApplication
public class MuseumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuseumApplication.class, args);
    }

}
