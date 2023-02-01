package com.siyu.messervice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SiYu
 */
@SpringBootApplication
@MapperScan("com.siyu.messervice.mapper")
public class MesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesServiceApplication.class, args);
    }

}
