package com.yzc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaTwo7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaTwo7002.class,args);
    }
}
