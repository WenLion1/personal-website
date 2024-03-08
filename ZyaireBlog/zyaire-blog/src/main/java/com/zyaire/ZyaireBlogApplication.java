package com.zyaire;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyaire.mapper")
public class ZyaireBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZyaireBlogApplication.class, args);
    }
}
