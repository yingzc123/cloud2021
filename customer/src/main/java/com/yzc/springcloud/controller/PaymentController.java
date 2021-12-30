package com.yzc.springcloud.controller;



import com.yzc.springcloud.feign.OrderFeign;
import com.yzc.springcloud.lb.LoadBalancer;
import com.yzc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    static final String Order_URL="http://CLOUD-ORDER-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OrderFeign orderFeign;

    @GetMapping("/getFeign")
    public Object getFeign(){
        return   orderFeign.getPort();
    }


    @GetMapping("/getLb")
    public Object getLb(){
        List<ServiceInstance> instancesList = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        if(null == instancesList || instancesList.size() < 1) {
            return null;
        }
        ServiceInstance instances = loadBalancer.instances(instancesList);
        URI uri = instances.getUri();
        System.out.println(uri);
        return   restTemplate.getForObject(uri+"/order/getPort", String.class);
    }

    @GetMapping("/getObject")
    public Object getObject(){
        return   restTemplate.getForObject(Order_URL+"/order/getPort", String.class);
    }

    @GetMapping("/getEntity")
    public Object getEntity(){
        return   restTemplate.getForEntity(Order_URL+"/order/getPort", String.class);
    }



}
