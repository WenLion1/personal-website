package com.zyaire;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.zyaire.mapper")
@EnableScheduling
public class ZyaireBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZyaireBlogApplication.class, args);
    }
}
