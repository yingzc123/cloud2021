package com.yzc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Gateway9999 {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9999.class,args);
    }
}
