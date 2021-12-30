package com.yzc.springcloud;


import com.yzc.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//自己被别的服务发现
@EnableDiscoveryClient
@EnableFeignClients
//熔断注解
@EnableHystrix
//@RibbonClient(value = "CLOUD-ORDER-SERVICE",configuration = MySelfRule.class)
public class customerMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(customerMain8001.class,args);
    }
}
