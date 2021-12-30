package com.yzc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//熔断注解
@EnableCircuitBreaker
//feign使用
@EnableFeignClients
public class NacosOpenService {
    public static void main(String[] args) {
        SpringApplication.run(NacosOpenService.class,args);
    }
}
